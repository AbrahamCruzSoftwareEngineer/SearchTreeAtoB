package com.example.searchtreeatob

class RouteTreeNode(val route: List<Node>) {
    var left: RouteTreeNode? = null
    var right: RouteTreeNode? = null
    val totalDistance: Int = route.sumOf { it.distance }
}

class BestRouteTree {
    private var root: RouteTreeNode? = null

    fun insert(route: List<Node>) {
        root = insertRec(root, route)
    }

    private fun insertRec(current: RouteTreeNode?, route: List<Node>): RouteTreeNode {
        if (current == null) {
            return RouteTreeNode(route)
        }
        val totalDistance = route.sumOf { it.distance }
        if (totalDistance < current.totalDistance) {
            current.left = insertRec(current.left, route)
        } else if (totalDistance > current.totalDistance) {
            current.right = insertRec(current.right, route)
        }
        return current
    }

    fun findOptimalRoute(): List<Node>? {
        var current = root
        while (current?.left != null) {
            current = current.left
        }
        return current?.route
    }
}