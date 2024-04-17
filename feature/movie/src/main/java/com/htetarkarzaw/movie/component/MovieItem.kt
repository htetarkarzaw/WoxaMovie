package com.htetarkarzaw.movie.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.htetarkarzaw.common.Endpoint
import com.htetarkarzaw.domain.model.Movie
import com.htetarkarzaw.movie.R


@Composable
fun MovieItem(
    modifier: Modifier = Modifier,
    movie: Movie,
    onItemClick: (Movie) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(12.dp))
            .padding(all = 12.dp)
            .clickable {
                onItemClick(movie)
            },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = modifier
                .fillMaxWidth()
                .padding(all = 8.dp),
            text = movie.originalTitle,
            style = MaterialTheme.typography.titleLarge
        )

        AsyncImage(
            model = Endpoint.MOVIES_IMAGE_URL + movie.backdropPath,
            contentDescription = "image",
            contentScale = ContentScale.Crop,
            modifier = modifier.aspectRatio(ratio = 2.0f),
            placeholder = painterResource(id = R.drawable.image_placeholder),
        )
        Text(
            modifier = modifier
                .fillMaxWidth()
                .padding(all = 8.dp),
            text = movie.overview,
            style = MaterialTheme.typography.labelSmall
        )
        Text(
            modifier = modifier
                .fillMaxWidth()
                .padding(all = 8.dp),
            text = "popularity : ${movie.popularity}",
            style = MaterialTheme.typography.labelSmall
        )
        Spacer(modifier = modifier.height(8.dp))
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "vote count : ${movie.voteCount}",
                style = MaterialTheme.typography.labelSmall
            )
            Text(
                text = "vote average : ${movie.voteAverage}",
                style = MaterialTheme.typography.labelSmall
            )
        }

    }
}

@Composable
@Preview(showBackground = true)
private fun Preview() {
    MovieItem(
        movie = Movie(
            backdropPath = "",
            overview = "This is the overview of the selected movie item. " +
                    "How's about the content preview. This gonna be GG. " +
                    "This overview will contain the description of a movie.",
            originalTitle = "Original Title",
            title = "Title",
            popularity = 1.2,
            releaseDate = "12.12.2022",
            voteCount = 1,
            voteAverage = 1.2,
            originalLanguage = "en"
        ),
        onItemClick = {}
    )
}