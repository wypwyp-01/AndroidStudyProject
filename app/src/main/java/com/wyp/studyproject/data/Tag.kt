package com.wyp.studyproject.data

data class TagInfo(
    val id: Long,
    val name: String,
    val coverPath: String? = null, // Path to an image representing the tag
    val extra1: String? = null,
    val extra2: String? = null
) {
    override fun toString(): String {
        return "id:$id,name:$name\n"
    }
}