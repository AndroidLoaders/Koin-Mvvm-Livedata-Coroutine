package com.githubbranching.koindependencyinjection

class Student(val courses: SchoolCourses, val friend: Friends) {

    fun beSmart() {
        courses.getCourses()
        friend.getFriends()
    }
}