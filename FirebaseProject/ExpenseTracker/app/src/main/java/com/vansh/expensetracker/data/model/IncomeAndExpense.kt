package com.vansh.expensetracker.data.model

data class IncomeAndExpense(
    val id: Int,
    val amount: Double,
    val type: String,
    val date: Long
) {
}