package com.autoservicecrm.master.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.autoservicecrm.R
import com.autoservicecrm.master.data.MasterRepository
import com.autoservicecrm.master.data.model.PostMasterDto
import com.autoservicecrm.master.ui.models.MasterScreenStateUiModel
import com.autoservicecrm.shared.ui.Event
import com.autoservicecrm.shared.ui.composable.dialog.models.Field
import com.autoservicecrm.shared.ui.composable.dialog.models.TextFieldsDialogUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MasterManagementViewModel @Inject constructor(
    private val masterRepository: MasterRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MasterScreenStateUiModel.getLoadingState())
    val uiState = _uiState.asStateFlow()

    private val _eventChannel = Channel<Event>(Channel.BUFFERED)
    val eventsFlow: Flow<Event>
        get() = _eventChannel.receiveAsFlow()

    init {
        updateCarList()
    }

    private fun updateCarList() {
        viewModelScope.launch {
            val masters = masterRepository.getMasters()
            val state = masters?.let {
                MasterScreenStateUiModel(masters = it)
            } ?: run {
                MasterScreenStateUiModel.getErrorState()
            }
            _uiState.emit(state)
        }
    }

    fun getNewMasterFields(): TextFieldsDialogUiModel {
        return TextFieldsDialogUiModel(
            R.string.create_master_title,
            R.string.create_master_subtitle,
            listOf(
                Field.TextInput(R.string.master_name_hint, MASTER_NAME),
                Field.TextInput(R.string.master_surname_hint, MASTER_SURNAME),
                Field.TextInput(R.string.master_phone_hint, MASTER_PHONE),
            )
        )
    }

    fun addNewMaster(map: Map<String, String>) {
        viewModelScope.launch {
            val result = masterRepository.postMaster(
                PostMasterDto(
                    name = map[MASTER_NAME] ?: "",
                    surname = map[MASTER_SURNAME] ?: "",
                    phone = map[MASTER_PHONE] ?: ""
                )
            )
            result?.let {
                _eventChannel.send(Event.Success)
                updateCarList()
            } ?: run {
                _eventChannel.send(Event.Error)
            }
        }
    }

    companion object {
        const val MASTER_NAME = "MASTER_NAME"
        const val MASTER_SURNAME = "MASTER_SURNAME"
        const val MASTER_PHONE = "MASTER_PHONE"
    }
}
