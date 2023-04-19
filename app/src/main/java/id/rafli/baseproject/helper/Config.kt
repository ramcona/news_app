package id.rafli.baseproject.helper

import android.os.Environment

object Config {

    const val BASE_URL = "https://tamasya.technice.id/"
    const val BASE_API = BASE_URL  + "api/"

    const val RAWG_TOKEN = "1a91ae741e244041a8c7b33e206733b8"

    val extra_model: String = "extraModel"
    val extra_type: String = "extraType"
    val extra_other:String = "extraOther"
    val extra_choose:String = "extra_choose"
    val extra_url: String = "extraUrl"
    val extra_list: String = "extraList"
    val extra_id: String = "extraID"

    var DIRECTORY_IMAGE: String = Environment.getExternalStorageDirectory().toString() + "/Tamasya/"

}