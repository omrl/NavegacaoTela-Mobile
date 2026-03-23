package com.example.navegacaotela

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navegacaotela.ui.theme.NavegacaoTelaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navigationController = rememberNavController()
            NavHost(
                navController = navigationController,
                startDestination = "/Tela1"
            ){
                composable("/Tela1"){
                    Tela1(
                        "Tela1",
                        clickAnterior = {
                            navigationController.navigate("/Tela1")
                        },
                        clickProximo = {
                            navigationController.navigate("/Tela2")
                        }
                    )
                }
                composable("/Tela2"){
                    Tela1("Tela2",
                        clickAnterior = {
                            navigationController.navigate("/Tela1")
                        },
                        clickProximo = {
                            navigationController.navigate("/Tela3")
                        }
                    )
                }
                composable("/Tela3"){
                    Tela1("Tela3",
                        clickAnterior = {
                            navigationController.navigate("/Tela2")
                        },
                        clickProximo = {
                            navigationController.navigate("/Tela3")
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun Tela1(
    nomeDaTela: String = "Tela 01",
    clickAnterior: () -> Unit = {},
    clickProximo: () -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        Text(nomeDaTela, fontSize = 36.sp)
        Button(onClick = {}) {
            Button(onClick = clickProximo) {
            Text("Próxima Tela")
            }
        }
        Button(onClick = {}) {
            Button(onClick = clickAnterior) {
            Text("Tela Anterior")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun preview(){
    Tela1("Tela 1")
}