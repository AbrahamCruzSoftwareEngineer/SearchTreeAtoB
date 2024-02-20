package com.example.searchtreeatob

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val resultTextView = findViewById<TextView>(R.id.resultTextView)
        val findRouteButton = findViewById<Button>(R.id.findRouteButton)
        val findRouteTreeButton = findViewById<Button>(R.id.findRouteTreeButton)

        // mock data for routes
        val allRoutes = listOf(
            listOf(Node("Start", 0), Node("A", 10), Node("B", 20), Node("Destination", 0)),
            listOf(Node("Start", 0), Node("C", 35), Node("Destination", 15)),
            listOf(Node("Start", 0), Node("D", 45), Node("Destination", 15))
        )

        // Tree initialization and route insertion
        val routeTree = BestRouteTree()
        allRoutes.forEach { route ->
            routeTree.insert(route)
        }

        //
        findRouteButton.setOnClickListener {
            val bestRouteResult = BestRouteResult()
            val optimalRoute = bestRouteResult.findOptimalRoute(allRoutes)
            val formattedOptimalRoute = formatRoute(optimalRoute)
            resultTextView.text = "Optimal Route (Sorting): $formattedOptimalRoute"
        }

        findRouteTreeButton.setOnClickListener {
            val optimalRoute = routeTree.findOptimalRoute()
            val formattedOptimalRoute =
                optimalRoute?.let { formatRoute(it) } ?: "No optimal route found"

            val treeString = buildString {
                append("Optimal Route (Tree): $formattedOptimalRoute\n")
            }
            resultTextView.text = treeString
            Log.d("Tree Represents: ", treeString)
        }
    }

    private fun formatRoute(route: List<Node>): String {
        val formattedRoute = StringBuilder()
        for ((index, node) in route.withIndex()) {
            formattedRoute.append("${node.location}(${node.distance}m)")
            if (index < route.size - 1) {
                formattedRoute.append(" -> ")
            }
        }
        return formattedRoute.toString()
    }
}