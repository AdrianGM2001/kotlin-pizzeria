package com.adrgon.practica1

import com.adrgon.practica1.modelo.IngredienteDTO
import com.adrgon.practica1.modelo.PizzaDTO
import com.adrgon.practica1.modelo.SIZE

fun main() {
    val ingredienteTomate = IngredienteDTO(id = 1, nombre = "Tomate", alergenos = emptyList())
    val ingredienteQueso = IngredienteDTO(id = 2, nombre = "Queso Mozzarella", alergenos = listOf("lactosa"))
    val ingredientePepperoni = IngredienteDTO(id = 3, nombre = "Pepperoni", alergenos = listOf("lactosa", "gluten", "soja"))
    val ingredientePimiento = IngredienteDTO(id = 4, nombre = "Pimiento", alergenos = emptyList())
    val ingredienteCebolla = IngredienteDTO(id = 5, nombre = "Cebolla", alergenos = emptyList())
    val ingredientePinya = IngredienteDTO(id = 6, nombre = "Piña", alergenos = emptyList())
    val ingredienteJamon = IngredienteDTO(id = 7, nombre = "Jamon", alergenos = listOf("lactosa"))
    val ingredienteQuesoGorgonzola = IngredienteDTO(id = 8, nombre = "Queso Gorgonzola", alergenos = listOf("lactosa"))
    val ingredienteQuesoParmesano = IngredienteDTO(id = 9, nombre = "Queso Parmesano", alergenos = listOf("lactosa"))
    val ingredienteAceitunas = IngredienteDTO(id = 10, nombre = "Aceitunas", alergenos = emptyList())
    val ingredienteAlbahaca = IngredienteDTO(id = 11, nombre = "Albahaca", alergenos = emptyList())
    val champinyones = IngredienteDTO(id = 12, nombre = "Champiñones", alergenos = emptyList())
    val ingredienteBacon = IngredienteDTO(id = 13, nombre = "Bacon", alergenos = listOf("gluten"))
    val ingredientePechugaPollo = IngredienteDTO(id = 14, nombre = "Pechuga de Pollo", alergenos = emptyList())
    val ingredientePesto = IngredienteDTO(id = 15, nombre = "Pesto", alergenos = listOf("frutos secos"))

    val pizzaMargherita = PizzaDTO(
        id = 1,
        nombre = "Pizza Margherita",
        precio = 8.50f,
        size = SIZE.MEDIANO,
        ingredientes = listOf(ingredienteTomate, ingredienteQueso, ingredienteAlbahaca)
    )

    val pizzaPepperoni = PizzaDTO(
        id = 2,
        nombre = "Pizza Pepperoni",
        precio = 10.00f,
        size = SIZE.GRANDE,
        ingredientes = listOf(ingredienteTomate, ingredienteQueso, ingredientePepperoni)
    )

    val pizzaVegetariana = PizzaDTO(
        id = 3,
        nombre = "Pizza Vegetariana",
        precio = 9.00f,
        size = SIZE.MEDIANO,
        ingredientes = listOf(ingredienteTomate, ingredienteQueso, ingredientePimiento, ingredienteCebolla, champinyones)
    )

    val pizzaHawaiana = PizzaDTO(
        id = 4,
        nombre = "Pizza Hawaiana",
        precio = 11.00f,
        size = SIZE.GRANDE,
        ingredientes = listOf(ingredienteTomate, ingredienteQueso, ingredienteJamon, ingredientePinya)
    )

    val pizzaCuatroQuesos = PizzaDTO(
        id = 5,
        nombre = "Pizza Cuatro Quesos",
        precio = 12.00f,
        size = SIZE.GRANDE,
        ingredientes = listOf(ingredienteTomate, ingredienteQueso, ingredienteQuesoGorgonzola, ingredienteQuesoParmesano)
    )

    val pizzaBBQPollo = PizzaDTO(
        id = 6,
        nombre = "Pizza BBQ Pollo",
        precio = 12.50f,
        size = SIZE.GRANDE,
        ingredientes = listOf(ingredienteTomate, ingredienteQueso, ingredientePechugaPollo, ingredienteBacon, ingredientePimiento)
    )

    val pizzaMediterranea = PizzaDTO(
        id = 7,
        nombre = "Pizza Mediterránea",
        precio = 13.00f,
        size = SIZE.GRANDE,
        ingredientes = listOf(ingredienteTomate, ingredienteQueso, ingredienteAceitunas, ingredientePimiento, ingredienteAlbahaca)
    )

    val pizzaChampinyonesYJamon = PizzaDTO(
        id = 8,
        nombre = "Pizza Champiñones y Jamón",
        precio = 10.50f,
        size = SIZE.MEDIANO,
        ingredientes = listOf(ingredienteTomate, ingredienteQueso, champinyones, ingredienteJamon)
    )

    val pizzaBaconYQueso = PizzaDTO(
        id = 9,
        nombre = "Pizza Bacon y Queso",
        precio = 11.50f,
        size = SIZE.GRANDE,
        ingredientes = listOf(ingredienteTomate, ingredienteQueso, ingredienteBacon, ingredienteQuesoGorgonzola)
    )

    val pizzaPestoPollo = PizzaDTO(
        id = 10,
        nombre = "Pizza Pesto Pollo",
        precio = 12.00f,
        size = SIZE.GRANDE,
        ingredientes = listOf(ingredientePesto, ingredienteQueso, ingredientePechugaPollo, ingredienteAlbahaca)
    )

    val controlador = Controlador()
    val pizzas = listOf(pizzaHawaiana, pizzaPepperoni, pizzaMargherita, pizzaVegetariana, pizzaMediterranea, pizzaBBQPollo, pizzaBaconYQueso, pizzaChampinyonesYJamon, pizzaCuatroQuesos, pizzaPestoPollo)
    val ingredientes = listOf(ingredienteTomate, ingredienteQueso, ingredientePepperoni, ingredientePimiento, ingredienteCebolla, ingredientePinya, ingredienteJamon, ingredienteQuesoGorgonzola, ingredienteQuesoParmesano, ingredienteAceitunas, ingredienteAlbahaca, champinyones, ingredienteBacon, ingredientePechugaPollo, ingredientePesto)

    // ORDEN ASCENDENTE
    println(controlador.ordenarPizzas(pizzas, ORDEN.ASCENDENTE))

    // ORDEN DESCENDENTE
    println(controlador.ordenarPizzas(pizzas, ORDEN.DESCENDENTE))

    // PIZZAS ENTRE 7 Y 10
    println(controlador.filtrarPizzas(pizzas))

    // PIZZAS ENTRE 1 Y 12
    println(controlador.filtrarPizzas(pizzas, 1f, 12f))

    // INGREDIENTES SIN LACTOSA
    println(controlador.filtrarIngredientes(listOf("lactosa"), ingredientes))

    // INGREDIENTES SIN LACTOSA Y GLUTEN
    println(controlador.filtrarIngredientes(listOf("lactosa", "gluten"), ingredientes))

    // NUMERO DE PIZZAS CON PEPPERONI
    println(controlador.contarPizzasConIngrediente(pizzas, ingredientePepperoni))

    // NUMERO DE PIZZAS CON QUESO
    println(controlador.contarPizzasConIngrediente(pizzas, ingredienteQueso))
}
