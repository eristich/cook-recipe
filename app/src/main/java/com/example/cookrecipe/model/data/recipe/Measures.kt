package com.example.cookrecipe.model.data.recipe

import com.example.cookrecipe.model.data.recipe.mesures.Metric
import com.example.cookrecipe.model.data.recipe.mesures.US

data class Measures(
    val us: List<US>?,
    val metric: List<Metric>,
)


