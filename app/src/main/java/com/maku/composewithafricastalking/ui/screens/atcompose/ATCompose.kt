package com.maku.composewithafricastalking.ui.screens.atcompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.maku.composewithafricastalking.ui.data.tabRowItems
import kotlinx.coroutines.launch

@Composable
fun ATComposeRoute(
    modifier: Modifier = Modifier
) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = true
        )
    }
    ATComposeScreen(modifier)
}

@Composable
fun ATComposeScreen(
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    val list = (0..5).map { it.toString() }
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            TabRow(
                selectedTabIndex = pagerState.currentPage,
                modifier = Modifier
                    .padding(15.dp, 0.dp, 15.dp, 0.dp)
                    .fillMaxWidth(),
                containerColor = Color.Transparent,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        // to try and fix this issue: https://github.com/google/accompanist/issues/1076#issuecomment-1087484721
//                        Modifier.pagerTabIndicatorOffset(
//                                pagerState, tabPositions
//                            ),
                        color =  Color.Transparent
                    )
                },
                divider = {
                    Divider(
                        thickness = 0.dp,
                        color =  Color.Transparent
                    )
                }
            ) {
                tabRowItems.forEachIndexed { index, item ->
                    val selected = pagerState.currentPage == index
                    val backgroundColor = if(selected) {
                        MaterialTheme.colorScheme.secondaryContainer
                    } else {
                        MaterialTheme.colorScheme.surface
                    }

                    val textColor = if(selected) {
                        MaterialTheme.colorScheme.onSecondaryContainer
                    } else {
                        MaterialTheme.colorScheme.onSurface
                    }

                    val clip = if(selected) {
                        RoundedCornerShape(30.dp)
                    } else {
                        RoundedCornerShape(0.dp)
                    }

                    Tab(
                        selected = selected,
                        onClick = { coroutineScope.launch { pagerState.scrollToPage(index) } },
                        modifier = Modifier
                            .clip(clip)
                            .background(
                                backgroundColor
                            ),
                        text = {
                            Text(
                                text = item.title,
                                color = textColor,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        },
                        selectedContentColor = MaterialTheme.colorScheme.onSecondary,
                        unselectedContentColor = MaterialTheme.colorScheme.secondary
                    )
                }
            }
            HorizontalPager(
                count = tabRowItems.size,
                state = pagerState
            ) { page ->
                tabRowItems[page].screen()
            }
        }
        item {
            Text(
                text = "History",
                modifier = Modifier
                    .padding(15.dp, 0.dp, 15.dp, 0.dp),
                fontSize = 24.sp,
                fontWeight = FontWeight.W700,
                textAlign = TextAlign.Start
            )
        }

        items(count = list.size) {
            Text(
                text = list[it],
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(15.dp, 0.dp, 15.dp, 0.dp)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ATComposeScreenPreview() {
    ATComposeScreen()
}

