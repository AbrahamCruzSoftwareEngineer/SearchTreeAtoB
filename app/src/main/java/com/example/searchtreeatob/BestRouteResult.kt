package com.example.searchtreeatob

data class Node(
    val location: String,
    val distance: Int
)

class BestRouteResult {

    private fun calculateCost(route: List<Node>): Int {
        var totalDistanceInMeters = 0

        for (i in 0 until route.size - 1) {
            totalDistanceInMeters += route[i].distance
        }

        return totalDistanceInMeters
    }

    fun findOptimalRoute(routes: List<List<Node>>): List<Node> {
        if (routes.isEmpty()) {
            // Handle the case when there are no routes available
            return emptyList()
        }
        val sortedRoutes = routes.sortedBy { calculateCost(it) }
        return sortedRoutes.first()
    }

}