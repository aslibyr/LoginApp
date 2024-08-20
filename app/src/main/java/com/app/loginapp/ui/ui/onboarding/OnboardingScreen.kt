package com.app.loginapp.ui.ui.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.loginapp.ui.components.button.CustomButton
import com.app.loginapp.ui.components.indicator.PagerIndicator
import com.app.loginapp.ui.ui.onboarding.pagerutils.PagerModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    pagerState: PagerState,
    pagerList: List<PagerModel>,
    onSkipClicked: () -> Unit,
) {
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f),
                userScrollEnabled = false
            ) { index ->
                val currentItem = pagerList[index]
                Text(
                    text = currentItem.text,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Box(
                modifier = Modifier
                    .padding(bottom = 12.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                PagerIndicator(
                    pagerState = pagerState,
                    indicatorSize = 8.dp,
                    indicatorCount = pagerList.size,
                    indicatorShape = CircleShape
                )
            }

            CustomButton(
                buttonText = "Geç",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                onClick = {
                    if (pagerState.currentPage == pagerState.pageCount.minus(1)) {
                        onSkipClicked()
                    } else {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage.plus(1))
                        }
                    }
                })
        }
    }
}
