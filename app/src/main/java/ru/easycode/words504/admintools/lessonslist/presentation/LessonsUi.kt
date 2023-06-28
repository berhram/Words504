package ru.easycode.words504.admintools.lessonslist.presentation

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.FileProvider
import java.io.File
import ru.easycode.words504.BuildConfig
import ru.easycode.words504.R

interface LessonsUi {
    fun map(adapter: AdminLessonsAdapter, context: Context)

    data class Initial(private val list: List<LessonUi>) : LessonsUi {
        override fun map(adapter: AdminLessonsAdapter, context: Context) = adapter.map(list)
    }

    data class Share(private val value: String) : LessonsUi {

        override fun map(adapter: AdminLessonsAdapter, context: Context) {
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
