package com.example.a12_081.ui.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.a12_081.ui.View.DestinasiHome
import com.example.a12_081.ui.View.HomeView
import com.example.a12_081.ui.View.Instruktur.DestinasiDetailInstruktur
import com.example.a12_081.ui.View.Instruktur.DestinasiHomeInstruktur
import com.example.a12_081.ui.View.Instruktur.DestinasiInsertInstruktur
import com.example.a12_081.ui.View.Instruktur.DestinasiUpdateInstruktur
import com.example.a12_081.ui.View.Instruktur.DetailInstrukturView
import com.example.a12_081.ui.View.Instruktur.HomeInstruktur
import com.example.a12_081.ui.View.Instruktur.InsertInstrukturView
import com.example.a12_081.ui.View.Instruktur.UpdateInstrukturView
import com.example.a12_081.ui.View.Kursus.DestinasiDetailKursus
import com.example.a12_081.ui.View.Kursus.DestinasiHomeKursus
import com.example.a12_081.ui.View.Kursus.DestinasiInsertKursus
import com.example.a12_081.ui.View.Kursus.DestinasiUpdateKursus
import com.example.a12_081.ui.View.Kursus.DetailKursusView
import com.example.a12_081.ui.View.Kursus.HomeKursus
import com.example.a12_081.ui.View.Kursus.InsertKursusView
import com.example.a12_081.ui.View.Kursus.UpdateKursusView
import com.example.a12_081.ui.View.Pendaftaran.DestinasiDetailPendaftaran
import com.example.a12_081.ui.View.Pendaftaran.DestinasiHomePendaftaran
import com.example.a12_081.ui.View.Pendaftaran.DestinasiInsertPendaftaran
import com.example.a12_081.ui.View.Pendaftaran.DestinasiUpdatePendaftaran
import com.example.a12_081.ui.View.Pendaftaran.DetailPendaftaranView
import com.example.a12_081.ui.View.Pendaftaran.HomePendaftaran
import com.example.a12_081.ui.View.Pendaftaran.InsertPendaftaranView
import com.example.a12_081.ui.View.Pendaftaran.UpdatePendaftaranView
import com.example.a12_081.ui.View.Siswa.DestinasiDetailSiswa
import com.example.a12_081.ui.View.Siswa.DestinasiHomeSiswa
import com.example.a12_081.ui.View.Siswa.DestinasiInsertSiswa
import com.example.a12_081.ui.View.Siswa.DestinasiUpdateSiswa
import com.example.a12_081.ui.View.Siswa.DetailSiswaView
import com.example.a12_081.ui.View.Siswa.HomeSiswa
import com.example.a12_081.ui.View.Siswa.InsertSiswaView
import com.example.a12_081.ui.View.Siswa.UpdateSiswaView

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
){
    NavHost(
        navController =navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier
    ) {
        composable(DestinasiHome.route){
            HomeView(
                navigateHomeSiswa = {navController.navigate(DestinasiHomeSiswa.route)},
                navigateHomeInstruktur = {navController.navigate(DestinasiHomeInstruktur.route)},
                navigateHomeKursus = {navController.navigate(DestinasiHomeKursus.route)},
                navigateHomePendaftaran = {navController.navigate(DestinasiHomePendaftaran.route)}
            )
        }
        composable(DestinasiHomeSiswa.route){
            HomeSiswa(
                navigateToInsert = {navController.navigate(DestinasiInsertSiswa.route)},
                onDetailClick = {id_siswa ->
                    navController.navigate("${DestinasiDetailSiswa.route}/$id_siswa")
                },
                navigateBack = {navController.navigate(DestinasiHome.route){
                    popUpTo(DestinasiHome.route){
                        inclusive=true
                    }
                } }

            )
        }
        composable(DestinasiHomeInstruktur.route){
            HomeInstruktur(
                navigateToInsert = {navController.navigate(DestinasiInsertInstruktur.route)},
                onDetailClick = {id_instruktur ->
                    navController.navigate("${DestinasiDetailInstruktur.route}/$id_instruktur")
                },
                navigateBack = {navController.navigate(DestinasiHome.route){
                    popUpTo(DestinasiHome.route){
                        inclusive=true
                    }
                } }
            )
        }
        composable(DestinasiHomeKursus.route){
            HomeKursus(
                navigateToInsert = {navController.navigate(DestinasiInsertKursus.route)},
                onDetailClick = {id_kursus ->
                    navController.navigate("${DestinasiDetailKursus.route}/$id_kursus")
                },
                navigateBack = {navController.navigate(DestinasiHome.route){
                    popUpTo(DestinasiHome.route){
                        inclusive=true
                    }
                } }
            )
        }
        composable(DestinasiHomePendaftaran.route){
            HomePendaftaran(
                navigateToInsert = {navController.navigate(DestinasiInsertPendaftaran.route)},
                onDetailClick = {id_pendaftaran ->
                    navController.navigate("${DestinasiDetailPendaftaran.route}/$id_pendaftaran")
                },
                navigateBack = {navController.navigate(DestinasiHome.route)}
            )
        }
        composable(DestinasiInsertSiswa.route){
            InsertSiswaView(
                navigateBack = {navController.navigate(DestinasiHomeSiswa.route){
                    popUpTo(DestinasiHomeSiswa.route){
                        inclusive = true
                    }
                }}
            )
        }
        composable(DestinasiInsertInstruktur.route){
            InsertInstrukturView(
                navigateBack = {navController.navigate(DestinasiHomeInstruktur.route){
                    popUpTo(DestinasiHomeInstruktur.route){
                        inclusive = true
                    }
                }}
            )
        }
        composable(DestinasiInsertKursus.route){
            InsertKursusView(
                navigateBack = {navController.navigate(DestinasiHomeKursus.route){
                    popUpTo(DestinasiHomeKursus.route){
                        inclusive = true
                    }
                }}
            )
        }
        composable(DestinasiInsertPendaftaran.route){
            InsertPendaftaranView(
                navigateBack = {navController.navigate(DestinasiHomePendaftaran.route){
                    popUpTo(DestinasiHomePendaftaran.route){
                        inclusive = true
                    }
                }}
            )
        }
        composable(DestinasiDetailSiswa.routeWithArgs,
            arguments = listOf(navArgument(DestinasiDetailSiswa.ID_SISWA){
                type = NavType.StringType
            })
        ){
            val id_siswa = it.arguments?.getString(DestinasiDetailSiswa.ID_SISWA)
            id_siswa?.let{
                DetailSiswaView(
                    NavigateBack = {navController.navigate(DestinasiHomeSiswa.route){
                        popUpTo(DestinasiHomeSiswa.route){
                            inclusive = true
                        }
                    } },
                    onEditClick = {
                        navController.navigate("${DestinasiUpdateSiswa.route}/$id_siswa")
                    },
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
        composable(DestinasiDetailInstruktur.routeWithArgs,
            arguments = listOf(navArgument(DestinasiDetailInstruktur.ID_INSTRUKTUR){
                type = NavType.StringType
            })
        ){
            val id_intruktur = it.arguments?.getString(DestinasiDetailInstruktur.ID_INSTRUKTUR)
            id_intruktur?.let{
                DetailInstrukturView(
                    NavigateBack = {navController.navigate(DestinasiHomeInstruktur.route){
                        popUpTo(DestinasiHomeInstruktur.route){
                            inclusive = true
                        }
                    } },
                    onEditClick = {
                        navController.navigate("${DestinasiUpdateInstruktur.route}/$id_intruktur")
                    },
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
        composable(DestinasiDetailKursus.routeWithArgs,
            arguments = listOf(navArgument(DestinasiDetailKursus.ID_KURSUS){
                type = NavType.StringType
            })
        ){
            val id_kursus = it.arguments?.getString(DestinasiDetailKursus.ID_KURSUS)
            id_kursus?.let{
                DetailKursusView(
                    NavigateBack = {navController.navigate(DestinasiHomeKursus.route){
                        popUpTo(DestinasiHomeKursus.route){
                            inclusive = true
                        }
                    } },
                    onEditClick = {
                        navController.navigate("${DestinasiUpdateKursus.route}/$id_kursus")
                    },
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
        composable(DestinasiDetailPendaftaran.routeWithArgs,
            arguments = listOf(navArgument(DestinasiDetailPendaftaran.ID_PENDAFTARAN){
                type = NavType.StringType
            })
        ){
            val id_pendaftaran = it.arguments?.getString(DestinasiDetailPendaftaran.ID_PENDAFTARAN)
            id_pendaftaran?.let{
                DetailPendaftaranView(
                    NavigateBack = {navController.navigate(DestinasiHomePendaftaran.route){
                        popUpTo(DestinasiHomePendaftaran.route){
                            inclusive = true
                        }
                    } },
                    onEditClick = {
                        navController.navigate("${DestinasiUpdatePendaftaran.route}/$id_pendaftaran")
                    },
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
        composable(DestinasiUpdateSiswa.routeWithArgs,
            arguments = listOf(navArgument(DestinasiDetailSiswa.ID_SISWA){
                type = NavType.StringType
            })
        ){
            val id_siswa = it.arguments?.getString(DestinasiUpdateSiswa.ID_SISWA)
            id_siswa?.let{
                UpdateSiswaView(
                    navigateBack ={navController.popBackStack()},
                    onNavigateUp = {navController.navigate(DestinasiHomeSiswa.route)},
                    id_siswa = id_siswa,
                )
            }
        }
        composable(DestinasiUpdateInstruktur.routeWithArgs,
            arguments = listOf(navArgument(DestinasiDetailInstruktur.ID_INSTRUKTUR){
                type = NavType.StringType
            })
        ){
            val id_intruktur = it.arguments?.getString(DestinasiUpdateInstruktur.ID_INSTRUKTUR)
            id_intruktur?.let{
                UpdateInstrukturView(
                    navigateBack ={navController.popBackStack()},
                    onNavigateUp = {navController.navigate(DestinasiHomeInstruktur.route)},
                    id_instruktur = id_intruktur,
                )
            }
        }
        composable(DestinasiUpdateKursus.routeWithArgs,
            arguments = listOf(navArgument(DestinasiDetailKursus.ID_KURSUS){
                type = NavType.StringType
            })
        ){
            val id_kursus = it.arguments?.getString(DestinasiUpdateKursus.ID_KURSUS)
            id_kursus?.let{
                UpdateKursusView(
                    navigateBack ={navController.popBackStack()},
                    onNavigateUp = {navController.navigate(DestinasiHomeKursus.route)},
                    id_kursus = id_kursus,
                )
            }
        }
        composable(DestinasiUpdatePendaftaran.routeWithArgs,
            arguments = listOf(navArgument(DestinasiDetailPendaftaran.ID_PENDAFTARAN){
                type = NavType.StringType
            })
        ){
            val id_pendaftaran = it.arguments?.getString(DestinasiUpdatePendaftaran.ID_PENDAFTARAN)
            id_pendaftaran?.let{
                UpdatePendaftaranView(
                    navigateBack ={navController.popBackStack()},
                    onNavigateUp = {navController.navigate(DestinasiHomePendaftaran.route)},
                    id_pendaftaran = id_pendaftaran
                )
            }
        }

    }
}