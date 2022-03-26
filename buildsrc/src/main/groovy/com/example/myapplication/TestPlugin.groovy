package com.example.myapplication

import org.gradle.api.Plugin
import org.gradle.api.Project

class TestPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.android.registerTransform(new TestTransform())
    }
}