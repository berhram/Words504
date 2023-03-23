package ru.easycode.rules

import com.pinterest.ktlint.core.RuleProvider
import com.pinterest.ktlint.core.RuleSetProviderV2

class EasyCodeRuleSetProvider : RuleSetProviderV2("easycode-rules", NO_ABOUT) {

    override fun getRuleProviders(): Set<RuleProvider> = setOf(
        RuleProvider {
            EncapsulationRule()
        }
    )
}
