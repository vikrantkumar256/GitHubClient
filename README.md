# GitHubClient - Android Application

## Overview
GitHubClient is a modern Android application that allows users to explore GitHub user profiles and repositories effortlessly. Built using Jetpack Compose, the app fetches data from the GitHub API to display a list of users, their detailed profiles, and top repository information with a clean and intuitive UI.

## Functionality Demo Video
https://github.com/user-attachments/assets/b8954c00-ab33-4b59-a857-f359d0197165

## Error and Loading
https://github.com/user-attachments/assets/db7426e3-43c9-4004-b9d9-fe374b0da6fb

## Key Features

### User List View
- Displays a list of GitHub users with essential details such as username, avatar, and bio.
- Users can tap on any profile to navigate to the detailed user view.

### User Detail View
- Showcases a comprehensive profile of a GitHub user.
- Displays bio, number of followers, following count, and other relevant stats.
- Lists the user's top public repositories.

### Repositories Overview
- Provides insights into a user’s repositories.
- Displays key repository details, including name, description, stars, and forks.
- Redirects to the original repository page on the GitHub website.

### Error Handling
- Displays an error screen when data retrieval from the GitHub API fails.
- Offers users the option to retry fetching the data.

### Loading Indicators
- Uses loading spinners to enhance user experience while fetching data.

### Smooth Navigation
- Implements Jetpack Compose’s Navigation Component for seamless transitions between screens.

## Technologies Used
- **Jetpack Compose**: Modern UI toolkit for native Android development.
- **Kotlin**: The primary programming language for the app.
- **StateFlow**: Used for reactive state management.
- **Kotlin Coroutines**: Manages background tasks efficiently.
- **Retrofit**: Fetches data from the GitHub REST API.
- **Hilt**: Dependency injection framework for easier dependency management.

## File Structure

### App Structure
```
GitHubClient/
│── data/
│   ├── mapper/
│   │   ├── RepoMapper.kt
│   │   ├── UserDetailMapper.kt
│   │   ├── UserMapper.kt
│   ├── model/
│   │   ├── RepoDto.kt
│   │   ├── UserDto.kt
│   │   ├── UserDetailDto.kt
│   ├── remote/
│   │   ├── GitHubApiService.kt
│   ├── repository/
│   │   ├── UserRepositoryImpl.kt
│
│── domain/
│   ├── model/
│   │   ├── Repo.kt
│   │   ├── User.kt
│   │   ├── UserDetail.kt
│   ├── repository/
│   │   ├── UserRepository.kt
│
├── ui/
│   ├── common/
│   │   ├── ErrorScreen.kt
│   │   └── LoadingScreen.kt
│   ├── navigation/
│   │   └── NavGraph.kt
│   ├── theme/
│   │   ├── Color.kt
│   │   ├── Theme.kt
│   │   └── Type.kt
│   ├── userdetail/
│   │   ├── component/
│   │   │   ├── UserDetailAdditionalInfo.kt
│   │   │   ├── UserDetailBio.kt
│   │   │   ├── UserDetailHeader.kt
│   │   │   ├── UserDetailStats.kt
│   │   │   └── UserRepositorySection.kt
│   │   ├── state/
│   │   │   ├── UserDetailState.kt
│   │   │   └── UserReposState.kt
│   │   ├── UserDetailScreen.kt
│   │   └── UserDetailViewModel.kt
│   └── userlist/
│       ├── component/
│       │   ├── UserList.kt
│       │   └── UserListItem.kt
│       ├── state/
│       │   └── UserListState.kt
│       ├── UserListScreen.kt
│       └── UserListViewModel.kt
│
│── util/
│   ├── ColorUtils.kt
│
│── di/
│   ├── AppModule.kt
│   ├── RepositoryModule.kt
│
│── CoreApplication.kt
│── MainActivity.kt
```

### Test Structure
```
GitHubClientTests/
│── data/
│   ├── mapper/
│   │   ├── RepoMapperTest.kt
│   │   ├── UserMapperTest.kt
│   │   ├── UserDetailMapperTest.kt
│   ├── remote/
│   │   ├── GitHubApiServiceTest.kt
│   ├── repository/
│   │   ├── UserRepositoryImplTest.kt
│
│── domain/
│   ├── repository/
│   │   ├── UserRepositoryTest.kt
│
│── ui/
│   ├── userlist/
│   │   ├── UserListViewModelTest.kt
│   ├── userdetail/
│   │   ├── UserDetailViewModelTest.kt
```

## Architecture
The app follows a **clean architecture pattern**, separating concerns into multiple layers:

### Data Layer
- Handles fetching, mapping, and storing data.
- Uses DTOs (Data Transfer Objects) to retrieve data from the GitHub API.
- Converts DTOs to domain models using mappers.

### Domain Layer
- Contains business logic and core data models.
- Defines repository interfaces for data operations.

### UI Layer
- Built with Jetpack Compose for reactive UI.
- Uses StateFlow for state management and updates the UI based on data changes.

## Dependency Injection (DI)
- Hilt manages dependencies, providing services like `GitHubApiService` and repositories where needed.

## Getting Started

### Clone the repository
```bash
git clone https://github.com/vikrantkumar256/GitHubClient.git
```

### Open the project in Android Studio
Ensure you have the required Android SDKs and dependencies installed.

### Sync Gradle and Build the Project
Click **File → Sync Project with Gradle Files**.

### Run the app using an emulator or physical device

## Future Considerations
- Implement authentication for GitHub account access.
- Add pagination for large repository lists.
- Integrate local storage for offline access.
- Support dark mode for user customization.

