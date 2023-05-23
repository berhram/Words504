package ru.easycode.words504.admintools.lessonslist.presentation

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.FileProvider
import ru.easycode.words504.BuildConfig
import ru.easycode.words504.R
import java.io.File

interface LessonsUi {
    fun map(adapter: AdminLessonsAdapter)

    data class Initial(private val list: List<LessonUi>) : LessonsUi {
        override fun map(adapter: AdminLessonsAdapter) = adapter.map(list)
    }

    data class Share(private val value: String) : LessonsUi {

        override fun map(adapter: AdminLessonsAdapter) {
            val context = adapter.provideContext()

            val tempFile = File(context.cacheDir, "lesson.json")
                .apply { writeText(value) }

            val contentUri = FileProvider.getUriForFile(
                context,
                BuildConfig.APPLICATION_ID + ".provider",
                tempFile
            )

            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "application/json"
                putExtra(Intent.EXTRA_STREAM, contentUri)
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }

            val chooserIntent = Intent.createChooser(
                shareIntent,
                context.getString(R.string.share_json_lesson)
            )
                .apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }

            val resInfoList = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                context.packageManager.queryIntentActivities(
                    chooserIntent,
                    PackageManager.ResolveInfoFlags.of(PackageManager.MATCH_DEFAULT_ONLY.toLong())
                )
            } else {
                context.packageManager.queryIntentActivities(
                    chooserIntent,
                    PackageManager.MATCH_DEFAULT_ONLY
                )
            }
            for (resolveInfo in resInfoList) {
                val packageName = resolveInfo.activityInfo.packageName
                context.grantUriPermission(
                    packageName,
                    contentUri,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
            }
            context.startActivity(chooserIntent)
        }
    }
}
