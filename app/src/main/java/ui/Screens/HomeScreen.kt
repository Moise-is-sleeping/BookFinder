package ui.Screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.calculator.bookfinder.header.Header
import com.calculator.bookfinder.header.lancelot
import com.calculator.bookfinder.homepagebooks.BookTitle

import com.calculator.bookfinder.homepagebooks.Books
import com.calculator.bookfinder.homepagebooks.ByAuthor
import com.calculator.bookfinder.naviagtionbar.NaviagtionBar

import com.calculator.bookfinder.ratings.Property1
import com.calculator.bookfinder.ratings.Ratings

import com.google.relay.compose.BoxScopeInstance.columnWeight
import com.google.relay.compose.BoxScopeInstance.rowWeight
import data.Routes.Routes
import ui.ViewModel.BookDatabaseViewModel
import ui.ViewModel.BookViewModel

/**
 * Function that displays books in the home screen
 *   @param bookDatabaseViewModel view-model that contains the logic behind certain functions in the screen
 *   @param bookViewModel view-model that contains the logic behind certain functions in the screen
 *   @param navController controller that allows navigation between screens
 */
@Composable
fun HomeScreen(bookViewModel: BookViewModel,navController:NavController,bookDatabaseViewModel: BookDatabaseViewModel){
    val list by bookViewModel.homeBookList.collectAsState()
    val ratingList by bookViewModel.ratingList.collectAsState()

    LaunchedEffect(Unit){
        bookDatabaseViewModel.fetchBooks()
    }

    Column (modifier= Modifier
        .fillMaxSize()
        .background(color = Color(0xFFE5DBD0))){
        Header(modifier = Modifier
            .fillMaxWidth()
            .height(60.dp))
        Text(text = "Recomended for you", fontFamily = lancelot, fontSize = 25.sp,       color = Color(
            alpha = 255,
            red = 0,
            green = 0,
            blue = 0
        ), modifier = Modifier.padding(15.dp))
        //if the list is empty, it shows the loading icon
        if (list.isEmpty()){
            Loading(120,90)
        }

        LazyColumn(modifier= Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.915f),horizontalAlignment = Alignment.CenterHorizontally){
            items(list){book ->
                        HomescreenBooks(title = book.title,
                            author = book.authors[0].name ,
                            picId = book.cover_id,
                            modifier = Modifier
                                .rowWeight(1.0f)
                                .columnWeight(1.0f)
                                .height(225.dp)
                                .width(390.dp)
                                .padding(top = 15.dp, bottom = 15.dp)
                                .clickable {
                                    bookViewModel.getBooks(book.key.substring(7))
                                    navController.navigate(Routes.BookDescriptionScreen.route)
                                    bookDatabaseViewModel.hasSavedDefaultValue(book.key.substring(7))
                                },
                            rating = ratingloader(ratingList,list.indexOf(book)) )
                }
            }
        NaviagtionBar(
            homeButton = {},
            searchButton = { navController.navigate(Routes.SearchScreen.route)},
            savedButton = {
                bookDatabaseViewModel.fetchBooks()
                navController.navigate(Routes.SavedScreen.route)
                          },
            modifier = Modifier
                .rowWeight(1.0f)
                .columnWeight(1.0f)
                .fillMaxWidth()
        )
        }

}

/**
 * Function that prevents the app from crashing by insuring that a result is given even if it hasnt loaded from the api...
 * yeah, should have probably done this differently and put it in the viewmodel, but i have a headache and its late
 *
 * @param list the list with the ratings
 * @param index the current index
 * @return returns 3f if the index is higher then the list size
 */
fun ratingloader(list : List<Float>,index:Int): Float {
    if(index < list.size){
        return list[index]
    }else{
        return 3f
    }
}

/**
 * Function that displays the books in the home screen
 * @param modifier enables visual editing of the function
 * @param title the book title
 * @param author the books author
 * @param picId the id in order to load the book cover
 * @param rating the ratings of the book
 */
@Composable
fun HomescreenBooks(modifier: Modifier = Modifier, title: String = "", author: String = "", picId: Int,rating: Float){
    com.calculator.bookfinder.homepagebooks.TopLevel(modifier = modifier) {
        Books(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 10.0.dp,
                    y = 11.0.dp
                )
            )
        ) {
            AsyncImage(model ="https://covers.openlibrary.org/b/id/$picId-M.jpg" , contentDescription ="test" ,modifier=Modifier.fillMaxSize())
        }
        BookTitle(
            title = title,
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 133.0.dp,
                    y = 21.0.dp
                )
            )
        )
        ByAuthor(
            author = author,
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 137.0.dp,
                    y = 89.0.dp
                )
            )
        )

        Ratings(
            property1 = RatingsDecider(rating),
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 133.0.dp,
                    y = 141.0.dp
                )
            )
        )
    }
}

/**
 * Function that chooses what variant to display based on the ratring of the book
 * @param rating the raing of the book
 * @return the chosen variant based on the rating
 */
fun RatingsDecider(rating :Float): Property1 {
    // if the rating is less the 2
    if (rating > 0 && rating < 2f){
        return Property1.Variant5
    }
    // if the rating is bewteen 2 and 3
    else if(rating > 2 && rating < 3f){
        return Property1.Variant4
    }
    // if its between 3 and 4
    else if(rating > 3 && rating < 4f){
        return Property1.Variant3
    }
    // if its between 4 and 4.5
    else if(rating > 4 && rating < 4.5f){
        return Property1.Variant2
    }
    else{
        return Property1.Default
    }
}

/**
 * Function that displays the loading icon
 * @param height the height
 * @param width the width
 */
@Composable
fun Loading(height:Int,width :Int){
    Row (modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center){
        CircularProgressIndicator(modifier = Modifier
            .height(height.dp)
            .width(width.dp))
    }

}




