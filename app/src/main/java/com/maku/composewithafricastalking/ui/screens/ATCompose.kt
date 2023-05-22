package com.maku.composewithafricastalking.ui.screens.atcompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.maku.airtime.data.tabRowItems
import com.maku.composewithafricastalking.ui.bottombar.AtComposeNavigationDefaults
import kotlinx.coroutines.launch

@Composable
fun ATComposeRoute(
    modifier: Modifier = Modifier
) {
    ATComposeScreen(modifier)
}

@Composable
fun ATComposeScreen(
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        val (tabs, pager, history) = createRefs()

        TabRow(
            selectedTabIndex = pagerState.currentPage,
            modifier = Modifier
                .constrainAs(tabs) {
                    top.linkTo(parent.top, 8.dp)
                    start.linkTo(parent.start, 8.dp)
                    end.linkTo(parent.end, 8.dp)
                    width = Dimension.fillToConstraints
                }
                .padding(0.dp, 0.dp, 0.dp, 8.dp)
                .fillMaxWidth(),
            containerColor = Color.Transparent,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    color = Color.Transparent
                )
            },
            divider = {
                Divider(
                    thickness = 0.dp,
                    color = Color.Transparent
                )
            }
        ) {
            tabRowItems.forEachIndexed { index, item ->
                val selected = pagerState.currentPage == index
                val backgroundColor = if (selected) {
                    MaterialTheme.colorScheme.primaryContainer
                } else {
                    MaterialTheme.colorScheme.surface
                }

                val clip = if (selected) {
                    RoundedCornerShape(30.dp)
                } else {
                    RoundedCornerShape(0.dp)
                }

                val textColor = if (selected) {
                    AtComposeNavigationDefaults.navigationSelectedItemColor()
                } else {
                    AtComposeNavigationDefaults.navigationContentColor()
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
                    selectedContentColor = MaterialTheme.colorScheme.primaryContainer,
                    unselectedContentColor = MaterialTheme.colorScheme.secondary
                )
            }
        }
        HorizontalPager(
            count = tabRowItems.size,
            modifier = Modifier
                .constrainAs(pager) {
                    top.linkTo(tabs.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                },
            state = pagerState
        ) { page ->
            tabRowItems[page].screen()
        }

//        LazyColumn(
//            modifier = modifier
//                .constrainAs(history) {
//                    top.linkTo(pager.bottom)
//                    start.linkTo(parent.start)
//                    end.linkTo(parent.end)
//                    width = Dimension.fillToConstraints
//                }
//                .fillMaxSize()
//                .padding(15.dp),
//            verticalArrangement = Arrangement.spacedBy(8.dp)
//        ) {
//            item {
//                Text(
//                    text = stringResource(
//                        id = R.string.history
//                    ),
//                    modifier = Modifier
//                        .padding(15.dp, 0.dp, 15.dp, 0.dp),
//                    fontSize = 24.sp,
//                    fontWeight = FontWeight.W700,
//                    textAlign = TextAlign.Start
//                )
//            }
//
//            items(count = list.size) {
//                Text(
//                    text = list[it],
//                    style = MaterialTheme.typography.bodyLarge,
//                    modifier = Modifier
//                        .padding(15.dp, 0.dp, 15.dp, 0.dp)
//                )
//            }
//        }
    }
}

@Composable
@Preview(
    name = "phone",
    uiMode = 32,
    showSystemUi = true,
    showBackground = false,
    device = "spec:shape=Normal,width=360,height=640,unit=dp,dpi=480"
)
fun ATComposeScreenPreview() {
    ATComposeScreen()
}
