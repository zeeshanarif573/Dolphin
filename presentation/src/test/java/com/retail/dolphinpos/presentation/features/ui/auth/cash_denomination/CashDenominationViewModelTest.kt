package com.retail.dolphinpos.presentation.features.ui.auth.cash_denomination

import app.cash.turbine.test
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CashDenominationViewModelTest {

    private lateinit var viewModel: CashDenominationViewModel

    @Before
    fun setup() {
        viewModel = CashDenominationViewModel()
    }

    @Test
    fun `initial state should have zero total amount`() = runTest {
        viewModel.totalAmount.test {
            val total = awaitItem()
            assertEquals(0.0, total, 0.01)
        }
    }

    @Test
    fun `should calculate total correctly when denomination count is updated`() = runTest {
        // Get initial denominations
        val denominations = viewModel.denominations.value
        val dollarOne = denominations.find { it.value == 1.0 }!!
        
        // Select and update count
        viewModel.selectDenomination(dollarOne)
        viewModel.updateCount(5)
        
        // Verify total is updated
        viewModel.totalAmount.test {
            val total = awaitItem()
            assertEquals(5.0, total, 0.01) // $1 * 5 = $5
        }
    }

    @Test
    fun `should handle multiple denominations correctly`() = runTest {
        val denominations = viewModel.denominations.value
        val dollarOne = denominations.find { it.value == 1.0 }!!
        val dollarFive = denominations.find { it.value == 5.0 }!!
        
        // Update $1 denomination
        viewModel.selectDenomination(dollarOne)
        viewModel.updateCount(3)
        
        // Update $5 denomination
        viewModel.selectDenomination(dollarFive)
        viewModel.updateCount(2)
        
        // Verify total
        viewModel.totalAmount.test {
            val total = awaitItem()
            assertEquals(13.0, total, 0.01) // (1*3) + (5*2) = 3 + 10 = 13
        }
    }

    @Test
    fun `should handle coin denominations correctly`() = runTest {
        val denominations = viewModel.denominations.value
        val quarter = denominations.find { it.value == 0.25 }!!
        
        viewModel.selectDenomination(quarter)
        viewModel.updateCount(4)
        
        viewModel.totalAmount.test {
            val total = awaitItem()
            assertEquals(1.0, total, 0.01) // $0.25 * 4 = $1.00
        }
    }

    @Test
    fun `should clear count correctly`() = runTest {
        val denominations = viewModel.denominations.value
        val dollarOne = denominations.find { it.value == 1.0 }!!
        
        // Set initial count
        viewModel.selectDenomination(dollarOne)
        viewModel.updateCount(5)
        
        // Clear count
        viewModel.clearCount()
        
        viewModel.totalAmount.test {
            val total = awaitItem()
            assertEquals(0.0, total, 0.01)
        }
    }

    @Test
    fun `should handle keypad input correctly`() = runTest {
        val denominations = viewModel.denominations.value
        val dollarOne = denominations.find { it.value == 1.0 }!!
        
        viewModel.selectDenomination(dollarOne)
        viewModel.addDigit("1")
        viewModel.addDigit("2")
        
        viewModel.currentCount.test {
            val count = awaitItem()
            assertEquals("12", count)
        }
        
        viewModel.totalAmount.test {
            val total = awaitItem()
            assertEquals(12.0, total, 0.01) // $1 * 12 = $12
        }
    }

    @Test
    fun `should handle double zero input correctly`() = runTest {
        val denominations = viewModel.denominations.value
        val dollarOne = denominations.find { it.value == 1.0 }!!
        
        viewModel.selectDenomination(dollarOne)
        viewModel.addDigit("1")
        viewModel.addDoubleZero()
        
        viewModel.currentCount.test {
            val count = awaitItem()
            assertEquals("100", count)
        }
    }

    @Test
    fun `should reset all denominations correctly`() = runTest {
        val denominations = viewModel.denominations.value
        val dollarOne = denominations.find { it.value == 1.0 }!!
        val dollarFive = denominations.find { it.value == 5.0 }!!
        
        // Set some counts
        viewModel.selectDenomination(dollarOne)
        viewModel.updateCount(3)
        viewModel.selectDenomination(dollarFive)
        viewModel.updateCount(2)
        
        // Reset all
        viewModel.resetAllDenominations()
        
        viewModel.totalAmount.test {
            val total = awaitItem()
            assertEquals(0.0, total, 0.01)
        }
    }
}
