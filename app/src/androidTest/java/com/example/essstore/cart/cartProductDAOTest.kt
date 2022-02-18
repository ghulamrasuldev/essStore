package com.example.essstore.cart

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import org.junit.runner.RunWith
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.essstore.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class cartProductDAOTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    //Variables
    private lateinit var database: cartProductDatabase
    private lateinit var dao: cartProductDAO

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            cartProductDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.cartProductDAO()
    }

    @After
    fun shutdown() {
        database.close()
    }

    @Test
    fun insertShoppingItem() = runBlockingTest {
        val shoppingItem = cartProduct(1, 1,"22", "22", 1, "11", "hello", "hello", 99,88,2)
        dao.addProductToCart(shoppingItem)
        val allItems = dao.readAllData().getOrAwaitValue()
        assertThat(allItems).contains(shoppingItem)
    }

    @Test
    fun updateSelectedQuantity() = runBlockingTest {
        var shoppingItem = cartProduct(1, 1,"22", "22", 1, "11", "hello", "hello", 99,88,2)
        dao.addProductToCart(shoppingItem)
        dao.UpdateProduct(shoppingItem.id, shoppingItem.selectedQuantity+1)
        shoppingItem.selectedQuantity += 1
        val allItems = dao.readAllData().getOrAwaitValue()
        assertThat(allItems).contains(shoppingItem)
    }

    @Test
    fun deleteProduct() = runBlockingTest {
        var shoppingItem = cartProduct(1, 1,"22", "22", 1, "11", "hello", "hello", 99,88,2)
        dao.addProductToCart(shoppingItem)
        dao.deleteProduct(shoppingItem.id)
        val allItems = dao.readAllData().getOrAwaitValue()
        assertThat(allItems).doesNotContain(shoppingItem)
    }
}