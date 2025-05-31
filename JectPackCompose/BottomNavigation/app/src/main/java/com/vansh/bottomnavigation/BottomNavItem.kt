import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem (
    val title: String,
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badges: Int
){
    companion object{
        val item: List<BottomNavItem> = listOf(
            BottomNavItem(
                title = "Home",
                route = "home",
                selectedIcon = Icons.Filled.Home,
                unselectedIcon = Icons.Outlined.Home,
                hasNews = false,
                badges = 0
            ),
            BottomNavItem(
                title = "Post",
                route = "post",
                selectedIcon = Icons.Filled.Category,
                unselectedIcon = Icons.Outlined.Category,
                hasNews = false,
                badges = 0
            ),
            BottomNavItem(
                title = "Notification",
                route = "notification",
                selectedIcon = Icons.Filled.Notifications,
                unselectedIcon = Icons.Outlined.Notifications,
                hasNews = false,
                badges = 4
            ),
            BottomNavItem(
                title = "Profile",
                route = "profile",
                selectedIcon = Icons.Filled.AccountCircle,
                unselectedIcon = Icons.Outlined.AccountCircle,
                hasNews = true,
                badges = 0
            )
        )
    }
}