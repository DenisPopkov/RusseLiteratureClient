package ru.popkov.android.core.feature.components.core.models

enum class SectionType(val sectionName: String) {
    ALL(sectionName = "все"),
    AUTHOR(sectionName = "писатели"),
    ARTICLE(sectionName = "статьи"),
    POET(sectionName = "поэты");
}

data class SectionFilterItem(
    val sectionType: SectionType = SectionType.AUTHOR,
    var isSectionSelected: Boolean = false,
)