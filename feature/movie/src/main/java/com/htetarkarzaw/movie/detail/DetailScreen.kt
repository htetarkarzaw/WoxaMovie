package com.htetarkarzaw.movie.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.htetarkarzaw.common.Endpoint
import com.htetarkarzaw.domain.model.MovieDetail
import com.htetarkarzaw.movie.R

@Composable
fun DetailScreen(
    movie: MovieDetail,
    onBackClick: () -> Unit
) {
    Scaffold {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it), contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(36.dp)
                        .padding(horizontal = 24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.clickable {
                            onBackClick()
                        },
                        painter = painterResource(id = R.drawable.ic_arrow_left),
                        contentDescription = null,
                        tint = Color.Black,
                    )
                    //title
                    Text(
                        text = "Details",
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight(600),
                        modifier = Modifier.padding(start = 24.dp)
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    Box {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(210.dp),
                            shape = RoundedCornerShape(
                                topStart = 0.dp,
                                topEnd = 0.dp,
                                bottomEnd = 20.dp,
                                bottomStart = 20.dp
                            ),
                        ) {
                            Box {
                                AsyncImage(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(210.dp),
                                    model = Endpoint.MOVIES_IMAGE_URL + movie.posterPath,
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    placeholder = painterResource(id = R.drawable.image_placeholder)
                                )

                                Card(
                                    modifier = Modifier
                                        .offset(x = 275.dp, y = 168.dp)
                                        .background(
                                            color = Color(0x52252836),
                                            shape = RoundedCornerShape(size = 8.dp)
                                        )
                                        .padding(
                                            start = 8.dp,
                                            top = 4.dp,
                                            end = 8.dp,
                                            bottom = 4.dp
                                        ),
                                    colors = CardDefaults.cardColors(
                                        containerColor = Color.Transparent
                                    ),
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_favorite),
                                            contentDescription = null,
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text(
                                            text = movie.voteAverage.toString(),
                                            fontSize = 12.sp,
                                        )
                                    }
                                }
                            }
                        }

                        Card(
                            modifier = Modifier
                                .offset(x = 29.dp, y = 150.dp)
                                .width(95.dp)
                                .height(120.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.Gray
                            ),
                            shape = RoundedCornerShape(16.dp),
                        ) {
                            AsyncImage(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(210.dp),
                                model = Endpoint.MOVIES_IMAGE_URL + movie.backdropPath,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                placeholder = painterResource(id = R.drawable.image_placeholder)
                            )
                        }

                        //Title
                        Text(
                            modifier = Modifier
                                .width(210.dp)
                                .height(48.dp)
                                .offset(x = 140.dp, y = 220.dp),
                            text = movie.title,
                            fontSize = 20.sp,
                        )

                    }

                    Spacer(modifier = Modifier.height(75.dp))

                    HorizontalThreeOptions(
                        yearRelease = movie.releaseDate,
                        duration = movie.runtime.toString(),
                        genre = movie.genre
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    //Description Title
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp),
                        text = "Description",
                        fontSize = 20.sp,
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    //Description
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp),
                        text = movie.overview,
                        textAlign = TextAlign.Justify,
                        fontSize = 14.sp,
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    //Genres Action * Horror * Comedy
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp),
                        text = movie.genres,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                    )

                }
            }
        }
    }
}

@Composable
fun HorizontalThreeOptions(
    yearRelease: String,
    duration: String,
    genre: String,
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            modifier = Modifier.size(16.dp),
            painter = painterResource(id = R.drawable.ic_calendar),
            contentDescription = null,
            tint = Color.Gray,
        )

        Spacer(modifier = Modifier.width(4.dp))

        //Year
        Text(
            text = yearRelease,
            color = Color.Black,
            fontSize = 14.sp,
            fontWeight = FontWeight(600),
        )

        Spacer(modifier = Modifier.width(12.dp))

        Icon(
            painter = painterResource(id = R.drawable.ic_vertical_line),
            contentDescription = null,
            tint = Color.Gray,
        )

        Spacer(modifier = Modifier.width(12.dp))

        Icon(
            painter = painterResource(id = R.drawable.ic_clock),
            contentDescription = null,
            tint = Color.Gray,
        )

        Spacer(modifier = Modifier.width(4.dp))

        //Duration
        Text(
            text = duration,
            color = Color.Black,
            fontSize = 14.sp,
        )

        Spacer(modifier = Modifier.width(12.dp))

        Icon(
            painter = painterResource(id = R.drawable.ic_vertical_line),
            contentDescription = null,
            tint = Color.Gray,
        )

        Spacer(modifier = Modifier.width(12.dp))

        Icon(
            painter = painterResource(id = R.drawable.ic_ticket),
            contentDescription = null,
            tint = Color.Gray,
        )

        Spacer(modifier = Modifier.width(4.dp))

        //Genre
        Text(
            text = genre,
            color = Color.Black,
            fontSize = 14.sp,
            fontWeight = FontWeight(600),
        )

    }
}

@Preview
@Composable
fun DetailsMovieContentPrev() {
    DetailScreen(
        movie = MovieDetail(),
        onBackClick = {}
    )
}