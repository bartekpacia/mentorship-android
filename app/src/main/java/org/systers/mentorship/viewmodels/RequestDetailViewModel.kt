package org.systers.mentorship.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.dsl.handleNetworkExceptionWithMessage
import org.systers.mentorship.remote.datamanager.RelationDataManager
import org.systers.mentorship.utils.CommonUtils
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeoutException

/**
 * This class represents the [ViewModel] used for Request Detail Screen
 */
class RequestDetailViewModel : ViewModel() {

    private val TAG = this::class.java.simpleName

    private val relationDataManager = RelationDataManager()

    val successful: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var message: String

    /**
     * Accepts a mentorship request
     * @param requestId id of the mentorship request
     */
    fun acceptRequest(requestId: Int) = viewModelScope.launch {
        try {
            message = relationDataManager.acceptRelationship(requestId).message
            successful.value = true
        } catch (throwable: Exception) {
            message = throwable.handleNetworkExceptionWithMessage(TAG)
            successful.value = false
        }
    }

    /**
     * Rejects a mentorship request
     * @param requestId id of the mentorship request
     */
    fun rejectRequest(requestId: Int) = viewModelScope.launch {
        try {
            message = relationDataManager.rejectRelationship(requestId).message
            successful.value = true
        } catch (throwable: Exception) {
            message = throwable.handleNetworkExceptionWithMessage(TAG)
            successful.value = false
        }
    }

    /**
     * Deletes a mentorship request
     * @param requestId id of the mentorship request
     */
    fun deleteRequest(requestId: Int) = viewModelScope.launch {
        try {
            message = relationDataManager.deleteRelationship(requestId).message
            successful.value = true
        } catch (throwable: Exception) {
            message = throwable.handleNetworkExceptionWithMessage(TAG)
            successful.value = false
        }
    }
}
