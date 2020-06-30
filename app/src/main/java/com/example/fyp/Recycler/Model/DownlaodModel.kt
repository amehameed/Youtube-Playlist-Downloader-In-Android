package com.example.fyp.Recycler.Model

class DownlaodModel {
    var name=""
    var id=""
    var status=false
    var title=""
    var imageurl=""
    var source=""
    var size=""
    constructor(name: String, id: String, status: Boolean, title: String, imageurl: String) {
        this.name = name
        this.id = id
        this.status = status
        this.title = title
        this.imageurl = imageurl
    }

    constructor(name: String, id: String, status: Boolean, title: String, imageurl: String, source: String) {
        this.name = name
        this.id = id
        this.status = status
        this.title = title
        this.imageurl = imageurl
        this.source = source
    }
}
