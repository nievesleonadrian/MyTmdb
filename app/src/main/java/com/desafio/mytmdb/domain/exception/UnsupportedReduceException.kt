package com.desafio.mytmdb.domain.exception

import com.desafio.mytmdb.common.mvi.MviResult
import com.desafio.mytmdb.common.mvi.MviUiState

class UnsupportedReduceException(state: MviUiState, result: MviResult) :
        RuntimeException("Cannot reduce state: $state with result: $result")