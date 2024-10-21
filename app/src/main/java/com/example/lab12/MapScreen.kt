package com.example.lab12

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Marker
import androidx.compose.ui.res.painterResource
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.example.lab12.R
import com.google.android.gms.maps.CameraUpdateFactory
import androidx.compose.ui.graphics.Color
import com.google.maps.android.compose.Polygon


@Composable
fun MapScreen() {
    val ArequipaLocation = LatLng(-16.4040102, -71.559611) // Arequipa, Perú
    val cameraPositionState = rememberCameraPositionState {
        position =
            com.google.android.gms.maps.model.CameraPosition.fromLatLngZoom(ArequipaLocation, 12f)
    }

    // Lista de ubicaciones
    val locations = listOf(
        LatLng(-16.433415, -71.5442652), // JLByR
        LatLng(-16.4205151, -71.4945209), // Paucarpata
        LatLng(-16.3524187, -71.5675994) // Zamacola
    )



    Box(modifier = Modifier.fillMaxSize()) {
        // Añadir GoogleMap al layout
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            // Añadir marcadores para cada ubicación
            locations.forEach { location ->
                Marker(
                    state = rememberMarkerState(position = location),
                    icon = BitmapDescriptorFactory.fromResource(R.drawable.ic_mountain), // Icono personalizado
                    title = "Ubicación",
                    snippet = "Punto de interés"
                )
            }
        }
        LaunchedEffect(Unit) {
            cameraPositionState.animate(
                update = CameraUpdateFactory.newLatLngZoom(LatLng(-16.2520984, -71.6836503), 12f), // Mover a Yura
                durationMs = 3000
            )
        }
    }
}