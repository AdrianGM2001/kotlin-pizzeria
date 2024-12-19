package com.adrgon.pizzeria.ui.home

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.adrgon.pizzeria.R
import com.adrgon.pizzeria.data.model.Size
import com.adrgon.pizzeria.data.model.Tipo
import com.adrgon.pizzeria.data.navigation.Screen
import com.adrgon.pizzeria.data.uimodel.TarjetaDTO
import com.adrgon.pizzeria.ui.componentes.TextConBordeEstiloPixelado
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.Locale

@Composable
fun HomeScreen(viewModel: HomeViewModel, navController: NavController) {
    val tarjetas: List<TarjetaDTO> by viewModel.tarjetas.observeAsState(listOf())
    val totalProductos: Int by viewModel.totalProductos.observeAsState(0)
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Drawer(navController, drawerState, scope, listOf(Screen.Home, Screen.Login))
        }
    ) {
        Scaffold(
            topBar = { Encabezado(totalProductos, scope, drawerState) },
            bottomBar = { Pie(viewModel::onCategoriaChange) },
            content = { paddingValues ->
                if (tarjetas.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                } else {
                    Home(
                        tarjetas = tarjetas,
                        paddingValues = paddingValues,
                        onTarjetaChange = viewModel::onTarjetaChange,
                        imagenAlergeno = viewModel::imagenAlergeno,
                        onAddCarrito = viewModel::onAddCarrito
                    )
                }
            }
        )
    }
}

@Composable
fun Drawer(
    navController: NavController,
    drawerState: DrawerState,
    scope: CoroutineScope,
    items: List<Screen>
) {
    ModalDrawerSheet(modifier = Modifier.width(250.dp)) {
        Column {
            items.forEach { screen ->
                DrawerItem(navController, screen)
                scope.launch { drawerState.close() }
            }
        }
    }
}

@Composable
fun DrawerItem(navController: NavController, screen: Screen) {
    when(screen) {
        Screen.Home -> {
            NavigationDrawerItem(
                label = { Text("Inicio") },
                selected = false,
                onClick = {
                    navController.navigate(screen.route) { launchSingleTop = true }
                }
            )
        }
        Screen.Login -> ConfirmarCerrarSesion(navController)
        Screen.Registro -> TODO()
    }
}

@Composable
fun ConfirmarCerrarSesion(navController: NavController) {
    var showDialog by remember { mutableStateOf(false) }

    NavigationDrawerItem(
        label = { Text("Cerrar sesión") },
        selected = false,
        onClick = {
            showDialog = true
        }
    )

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = {
                Text(text = "Cierre de sesión")
            },
            text = {
                Text("¿Estás seguro de que quieres cerrar sesión?")
            },
            confirmButton = {
                TextButton(onClick = {
                    showDialog = false
                    navController.navigate(Screen.Login.route)
                }) {
                    Text("Sí")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("No")
                }
            }
        )
    }
}

@Composable
fun Home(
    tarjetas: List<TarjetaDTO>,
    paddingValues: PaddingValues,
    onTarjetaChange: (TarjetaDTO) -> Unit,
    imagenAlergeno: (String) -> Int,
    onAddCarrito: (TarjetaDTO) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background).
            padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Seccion(tarjetas, imagenAlergeno, onTarjetaChange, onAddCarrito)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Encabezado(totalProductos: Int, scope: CoroutineScope, drawerState: DrawerState) {
    TopAppBar(
        title = {
            Row (
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { scope.launch { drawerState.open() } }
                ) {
                    Icon(
                        Icons.Filled.Menu,
                        contentDescription = "Menú",
                        modifier = Modifier.size(32.dp)
                    )
                }
                Image(
                    painter = painterResource(R.drawable.logo),
                    contentDescription = stringResource(R.string.app_name)
                )
            }
        },
        actions = {
            BadgedBox(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(end = 16.dp),
                badge = {
                    Badge {
                        Text(
                            text = if (totalProductos < 100) totalProductos.toString() else "99+",
                        )
                    }
                }
            ) {
                IconButton(
                    onClick = { /*TODO*/ }
                ) {
                    Icon(
                        Icons.Filled.ShoppingCart,
                        contentDescription = "Ir al carrito",
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        }
    )
}

@Composable
fun Pie(onCategoriaChange: (Tipo) -> Unit) {
    NavigationBar {
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            items(Tipo.entries) {
                FilledTonalButton(
                    onClick = { onCategoriaChange(it) }
                ) {
                    Text(
                        text = it.seccion,
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun Seccion(
    tarjetas: List<TarjetaDTO>,
    imagenAlergeno: (String) -> Int,
    onTarjetaChange: (TarjetaDTO) -> Unit,
    onAddCarrito: (TarjetaDTO) -> Unit
) {
    // TODO: Hacer que se reinicie el scroll al cambiar de categoría
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(tarjetas) {
            TarjetaProducto(it, imagenAlergeno, onTarjetaChange, onAddCarrito)
        }
    }
}

@Composable
fun TarjetaProducto(
    tarjeta: TarjetaDTO,
    imagenAlergeno: (String) -> Int,
    onTarjetaChange: (TarjetaDTO) -> Unit,
    onAddCarrito: (TarjetaDTO) -> Unit
) {
    OutlinedCard(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TituloTarjeta(tarjeta)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Column {
                    ImagenPrecioTarjeta(tarjeta)
                }
                if (tarjeta.tieneIngredientes)
                    Column(
                        modifier = Modifier.padding(4.dp)
                    ) {
                        IngredientesTarjeta(tarjeta)
                        AlergenosTarjeta(tarjeta, imagenAlergeno)
                    }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CantidadTarjeta(tarjeta, onTarjetaChange)
                if (tarjeta.tieneSize)
                    SizeTarjeta(tarjeta, onTarjetaChange)
                CarritoTarjeta(tarjeta, onAddCarrito)
            }
        }
    }
}

@Composable
fun TituloTarjeta(tarjeta: TarjetaDTO) {
    Text(
        text = tarjeta.producto.nombre,
        style = MaterialTheme.typography.labelLarge,
        modifier = Modifier.padding(bottom = 4.dp)
    )
}

@Composable
fun ImagenPrecioTarjeta(tarjeta: TarjetaDTO) {
    Box(
        contentAlignment = Alignment.BottomEnd
    ) {
        Image(
            painter = painterResource(tarjeta.idImagen),
            contentDescription = tarjeta.producto.nombre,
            modifier = Modifier.height(150.dp)
        )
        Box {
            TextConBordeEstiloPixelado(
                String.format(
                    Locale.getDefault(),
                    "%.2f€",
                    tarjeta.producto.precio
                )
            )
        }
    }
}

@Composable
fun IngredientesTarjeta(tarjeta: TarjetaDTO) {
    Text(
        text = tarjeta.producto.ingredientes.joinToString(
            separator = "\n",
            transform = { "- ${it.nombre}" }
        ),
        style = MaterialTheme.typography.labelSmall,
        modifier = Modifier.padding(start = 8.dp)
    )
}

@Composable
fun AlergenosTarjeta(tarjeta: TarjetaDTO, imagenAlergeno: (String) -> Int) {
    // .flatMap -> Lista de listas a lista (mejor que iterar una dentro de otra)
    val alergenos = tarjeta.producto.ingredientes.flatMap { it.alergenos }.distinct().sorted()
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .width(220.dp)
            .padding(top = 8.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        alergenos.forEach {
            IconButton(
                onClick = { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() },
                modifier = Modifier.size(36.dp).padding(start = 4.dp)
            ) {
                Image(
                    painter = painterResource(imagenAlergeno(it)),
                    contentDescription = it,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(32.dp).clip(CircleShape)
                )
            }
        }
    }
}

@Composable
fun CantidadTarjeta(tarjeta: TarjetaDTO, onTarjetaChange: (TarjetaDTO) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { onTarjetaChange(tarjeta.copy(cantidad = tarjeta.cantidad - 1)) },
            enabled = tarjeta.habilitarQuitar
        ) {
            Icon(
                Icons.Filled.Remove,
                contentDescription = "Quitar"
            )
        }
        Text(
            text = tarjeta.cantidad.toString(),
            style = MaterialTheme.typography.titleMedium
        )
        IconButton(
            onClick = { onTarjetaChange(tarjeta.copy(cantidad = tarjeta.cantidad + 1)) },
            enabled = tarjeta.habilitarAnyadir
        ) {
            Icon(
                Icons.Filled.Add,
                contentDescription = "Añadir"
            )
        }
    }
}

@Composable
fun SizeTarjeta(tarjeta: TarjetaDTO, onTarjetaChange: (TarjetaDTO) -> Unit) {
    Box(
        modifier = Modifier.width(130.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        TextButton(
            onClick = { onTarjetaChange(tarjeta.copy(expanded = true)) }
        ) {
            Text(
                text = tarjeta.size?.nombre ?: "¡Elige el tamaño!",
            )
        }
        MaterialTheme(
            shapes = MaterialTheme.shapes.copy(extraSmall = RoundedCornerShape(16.dp))
        ) {
            DropdownMenu(
                expanded = tarjeta.expanded,
                onDismissRequest = { onTarjetaChange(tarjeta.copy(expanded = false)) }
            ) {
                for (size in Size.entries) {
                    DropdownMenuItem(
                        text = { Text(size.nombre) },
                        onClick = { onTarjetaChange(tarjeta.copy(expanded = false, size = size)) }
                    )
                }
            }
        }
    }
}

@Composable
fun CarritoTarjeta(tarjeta: TarjetaDTO, onAddCarrito: (TarjetaDTO) -> Unit) {
    val context = LocalContext.current
    IconButton(
        enabled = tarjeta.habilitarCarrito,
        onClick = {
            onAddCarrito(tarjeta)
            Toast.makeText(
                context,
                "${tarjeta.cantidad} x ${tarjeta.producto.nombre} ${tarjeta.size?.nombre ?: ""} añadidos",
                Toast.LENGTH_SHORT
            ).show()
        }
    ) {
        Icon(
            Icons.Filled.AddShoppingCart,
            contentDescription = "Añadir al carrito"
        )
    }
}