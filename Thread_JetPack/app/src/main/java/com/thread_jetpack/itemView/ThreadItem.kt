package com.thread_jetpack.itemView

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.thread_jetpack.R
import com.thread_jetpack.component.SharedPrefConstants
import com.thread_jetpack.model.ThreadModel
import com.thread_jetpack.model.UserModel
import com.thread_jetpack.utils.SharedfPref

@Composable
fun ThreadItem(
    threadModel: ThreadModel,
    userModel: UserModel,
    userId : String,
    navController : NavHostController
) {
    val context = LocalContext.current
    Column {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            val (userImage, userName, date, time, image, title) = createRefs()

            Image(
                painter = rememberAsyncImagePainter(userModel.image),
                contentDescription = null,
                modifier = Modifier
                    .constrainAs(userImage) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
                    .size(40.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Text(
                userModel.name,
                style = TextStyle(fontSize = 20.sp),
                modifier = Modifier.constrainAs(userName) {
                    start.linkTo(userImage.end, margin = 10.dp)
                    top.linkTo(userImage.top)
                    bottom.linkTo(userImage.bottom)
                }
            )
            Text(
                threadModel.thread,
                style = TextStyle(fontSize = 15.sp),
                modifier = Modifier.constrainAs(title) {
                    start.linkTo(userName.start)
                    top.linkTo(userName.bottom, margin = 5.dp)
                }
            )
            if (threadModel.image != ""){
                Card(modifier = Modifier
                    .constrainAs(image) {
                        top.linkTo(title.bottom, margin = 5.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }) {
                    Image(
                        painter = rememberAsyncImagePainter(threadModel.image),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            }


        }

        Divider(color = Color.LightGray, thickness = 1.dp)
    }
}
