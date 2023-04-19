package id.rafli.baseproject.base

import id.rafli.baseproject.network.ClientService


open class BaseHelper {

    val ApiServiceServer by lazy { ClientService().create(file = false, isLogin = false) }
    val ApiServiceServerLogin by lazy { ClientService().create(file = false, isLogin = true) }
    val ApiServiceServerFile by lazy { ClientService().create(true) }


}