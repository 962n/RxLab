package com.example.a962n.rxlab

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import com.example.a962n.rxlab.ui.main.MainViewModel
import kotlinx.coroutines.awaitAll
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

//@RunWith(RobolectricTestRunner::class)
class MainViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun observe() {

//        val viewModel = MainViewModel(TestSchedulerProvider())
//
//        val dataObserver = { data: Long ->
//            println("dataObserver receive $data")
//        }
//        viewModel.data.observeForever(dataObserver)
//        val strObserver = { str: String ->
//            println("strObserver receive $str")
//        }
//        viewModel.str.observeForever(strObserver)
//
//        Thread.sleep(5000)

//        viewModel.data.removeObserver(dataObserver)
//        viewModel.str.removeObserver(strObserver)

    }
    @Test
    fun test() {
        val A = MutableLiveData<Long>()
        val B = A.switchMap{
            MutableLiveData(it * 2)
        }
        B.observeForever {
            println("通知が来ました！！値は${it}です")
        }
        A.value = 1
        A.value = 2
        A.value = 3
        A.value = 4
        A.value = 5
    }


}
