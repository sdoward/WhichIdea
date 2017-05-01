package com.sdoward.preference.setup

import com.sdoward.preference.signin.User

data class SetupViewState(val loading: Boolean, val users: List<User>)