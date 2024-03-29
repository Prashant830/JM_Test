package com.prashant830.jmtest.core.networkanddatalayer.entity.model

data class VectorSize(
    val formats: List<Format>,
    val size: Int,
    val size_height: Int,
    val size_width: Int,
    val target_sizes: List<List<Int>>
)