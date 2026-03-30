package com.example.navegacaotela

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navigationController = rememberNavController()

                    NavHost(
                        navController = navigationController,
                        startDestination = "/Tela1"
                    ) {
                        composable("/Tela1") {
                            Tela1(
                                nomeDaTela = "Charada Game",
                                textoBotao = "Começar",
                                mostrarBotaoVoltar = false,
                                clickProximo = {
                                    navigationController.navigate("/Tela2")
                                }
                            )
                        }
                        composable("/Tela2") {
                            Tela2("dica-1",
                                onAcerto = {
                                    navigationController.navigate("/Tela-final")
                                },
                                clickAnterior = {
                                    navigationController.popBackStack()
                                },
                                clickProximo = {
                                    navigationController.navigate("/Tela3")
                                }
                            )
                        }
                        composable("/Tela3") {
                            Tela3("dica-2",
                                onAcerto = {
                                    navigationController.navigate("/Tela-final")
                                },
                                clickAnterior = {
                                    navigationController.popBackStack()
                                },
                                clickProximo = {
                                    navigationController.navigate("/Tela4")
                                }
                            )
                        }
                        composable("/Tela4") {
                            Tela4("dica-3",
                                clickAnterior = {
                                    navigationController.popBackStack()
                                },
                                clickProximo = {
                                    navigationController.navigate("/Tela-final")
                                }
                            )
                        }
                        composable("/Tela-final") {
                            TelaVitoria(
                                onRecomecar = {
                                    navigationController.navigate("/Tela1") {
                                        popUpTo("/Tela1") { inclusive = true }
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Tela1(
    nomeDaTela: String = "Charada Game",
    textoBotao: String = "Começar",
    mostrarBotaoVoltar: Boolean = true,
    clickProximo: () -> Unit = {},
    clickAnterior: () -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        Text(nomeDaTela, fontSize = 28.sp, textAlign = TextAlign.Center, modifier = Modifier.padding(16.dp))
        Button(onClick = clickProximo) {
            Text(textoBotao)
        }
        if (mostrarBotaoVoltar) {
            Button(onClick = clickAnterior) {
                Text("Voltar")
            }
        }
    }
}

@Composable
fun Tela2(
    nomeDaTela: String = "dica-1",
    onAcerto: () -> Unit = {},
    clickProximo: () -> Unit = {},
    clickAnterior: () -> Unit = {}
){
    val textoDigitado = remember { mutableStateOf("") }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        Text(
            text = "Dica 1: Quando criança passou por dificuldades e teve problemas cardíacos que quase o impossibilitaram de se tornar o que é hoje",
            textAlign = TextAlign.Center,
            fontSize = 18.sp
        )
        
        OutlinedTextField(
            value = textoDigitado.value,
            onValueChange = { textoDigitado.value = it },
            label = { Text("Digite seu palpite") }
        )
        
        Button(onClick = {
            if (textoDigitado.value.trim().equals("Cristiano Ronaldo", ignoreCase = true)) {
                onAcerto()
            }
        }) {
            Text("Verificar Resposta")
        }

        Button(onClick = clickProximo) {
            Text("Próxima dica")
        }
        
        Button(onClick = clickAnterior) {
            Text("Voltar")
        }
    }
}

@Composable
fun Tela3(
    nomeDaTela: String = "dica-2",
    onAcerto: () -> Unit = {},
    clickProximo: () -> Unit = {},
    clickAnterior: () -> Unit = {}
){
    val textoDigitado = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        Text(
            text = "Dica 2: Nasceu na Ilha da Madeira",
            textAlign = TextAlign.Center,
            fontSize = 18.sp
        )

        OutlinedTextField(
            value = textoDigitado.value,
            onValueChange = { textoDigitado.value = it },
            label = { Text("Digite seu palpite") }
        )

        Button(onClick = {
            if (textoDigitado.value.trim().equals("Cristiano Ronaldo", ignoreCase = true)) {
                onAcerto()
            }
        }) {
            Text("Verificar Resposta")
        }

        Button(onClick = clickProximo) {
            Text("Próxima dica")
        }

        Button(onClick = clickAnterior) {
            Text("Voltar")
        }
    }
}

@Composable
fun Tela4(
    nomeDaTela: String = "dica-3",
    clickProximo: () -> Unit = {},
    clickAnterior: () -> Unit = {}
){
    val textoDigitado = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        Text(
            text = "Dica Final: Sua carreira futebolística é muito vitoriosa",
            textAlign = TextAlign.Center,
            fontSize = 18.sp
        )

        OutlinedTextField(
            value = textoDigitado.value,
            onValueChange = { textoDigitado.value = it },
            label = { Text("Digite seu palpite") }
        )

        Button(onClick = {
            if (textoDigitado.value.trim().equals("Cristiano Ronaldo", ignoreCase = true)) {
                clickProximo()
            }
        }) {
            Text("Verificar Resposta")
        }

        Button(onClick = clickAnterior) {
            Text("Voltar")
        }
    }
}

@Composable
fun TelaVitoria(
    onRecomecar: () -> Unit
) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        val mediaPlayer = MediaPlayer.create(context, R.raw.cr_suuu)
        mediaPlayer.start()
        mediaPlayer.setOnCompletionListener { it.release() }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.cr7siwww),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
        ) {
            Text(
                "SIIIWWWWWW! Você acertou!",
                fontSize = 32.sp,
                color = androidx.compose.ui.graphics.Color.White,
                textAlign = TextAlign.Center
            )
            Button(onClick = onRecomecar) {
                Text("Recomeçar")
            }
        }
    }
}

//@Preview(showBackground = true)
@Composable
fun PreviewTela1(){
    Tela1("Tela 1")
}
