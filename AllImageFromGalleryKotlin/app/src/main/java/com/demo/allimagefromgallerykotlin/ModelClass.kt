package com.demo.allimagefromgallerykotlin

import java.util.ArrayList

class ModelClass {
    lateinit var folderName: String
    lateinit var imagePath: ArrayList<String>

    constructor(){

    }
    constructor(folderName: String, imagePath: ArrayList<String>){
        this.folderName=folderName
        this.imagePath=imagePath
    }

}