package ui.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.calculator.bookfinder.header.Header
import com.google.relay.compose.BoxScopeInstance.columnWeight
import com.google.relay.compose.BoxScopeInstance.rowWeight


@Composable
fun HomeScreen(){
    Column (modifier=Modifier.fillMaxSize()){
        Header(
            userAccountButton = {},
            notificationButton = {},
            modifier = Modifier.rowWeight(1.0f).columnWeight(1.0f).fillMaxHeight(0.1f).fillMaxWidth()
        )
    }
}