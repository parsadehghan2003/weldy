package com.ftpd.cat.ui

data class Change<out T>(
        val oldData: T,
        val newData: T
)