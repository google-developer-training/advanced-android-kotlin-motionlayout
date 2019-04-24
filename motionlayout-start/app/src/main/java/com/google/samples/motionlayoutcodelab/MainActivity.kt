/*
 *   Copyright (C) 2019 The Android Open Source Project
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.google.samples.motionlayoutcodelab

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlin.reflect.KClass

data class Step(
    val number: String,
    val name: String,
    val caption: String,
    val activity: KClass<out Activity>,
    val highlight: Boolean = false)

private val data = listOf(
    Step("Step 1",
        "Animations with Motion Layout",
        "Learn how to build a basic animation with Motion Layout. This will crash until you complete the step in the codelab.",
        Step1Activity::class
    ),
    Step("Step 2",
        "Animating based on drag events",
        "Learn how to control animations with drag events. This will not display any animation until you complete the step in the codelab.",
        Step2Activity::class
    ),
    Step("Step 3",
        "Modifying a path",
        "Learn how to use KeyFrames to modify a path between start and end.",
        Step3Activity::class
    ),
    Step("Step 4",
        "Building complex paths",
        "Learn how to use KeyFrames to build complex paths through multiple KeyFrames.",
        Step4Activity::class
    ),
    Step("Step 5",
        "Changing attributes with motion",
        "Learn how to resize and rotate views during animations.",
        Step5Activity::class
    ),
    Step("Step 6",
        "Changing custom attributes",
        "Learn how to change custom attributes during motion.",
        Step6Activity::class
    ),
    Step("Step 7",
        "OnSwipe with complex paths",
        "Learn how to control motion through complex paths with OnSwipe.",
        Step7Activity::class
    ),
    Step("Completed: Steps 2-7",
        "Steps 2-7 completed",
        "All changes in steps 2-7 applied",
        Step7CompletedActivity::class,
        highlight = true
    ),
    Step("Step 8",
        "Running motion with code",
        "Learn how to use MotionLayout to build complex collapsing toolbar animations.",
        Step8Activity::class
    ),
    Step("Completed: Step 8 ",
        "Implements running motion with code",
        "Changes applied from step 8",
        Step8CompletedActivity::class,
        highlight = true
    )
)

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = MainAdapter(data)
    }

}

class MainAdapter(val data: List<Step>) : RecyclerView.Adapter<MainViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MainViewHolder(view as CardView)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(data[position])
    }

}

class MainViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView) {
    val header: TextView = cardView.findViewById(R.id.header)
    val description: TextView = cardView.findViewById(R.id.description)
    val caption: TextView = cardView.findViewById(R.id.caption)

    fun bind(step: Step) {
        header.text = step.number
        description.text = step.name
        caption.text = step.caption
        val context = cardView.context
        cardView.setOnClickListener {
            val intent = Intent(context, step.activity.java)
            context.startActivity(intent)
        }
        val color = if (step.highlight) {
            context.resources.getColor(R.color.secondaryLightColor)
        } else {
            context.resources.getColor(R.color.primaryTextColor)
        }
        header.setTextColor(color)
        description.setTextColor(color)
    }

}
