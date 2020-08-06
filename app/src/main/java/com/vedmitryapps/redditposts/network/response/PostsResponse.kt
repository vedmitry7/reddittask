package com.vedmitryapps.redditposts.network.response

import com.google.gson.annotations.SerializedName

data class PostsResponse (
	@SerializedName("kind") val kind : String,
	@SerializedName("data") val data : Data
)