
# Check-it | To-do app list backend

This is an API service for a to-do list type app called "Check-it". This is a personal project built with Java, Spring Boot, Java Security, JWT, and Docker. The service is hosted in "www.render.com"





## Demo

As mentioned in the introduction paragraph, the services in hosted in "www.render.com". The Base URL for this service is:

https://portfolio-checkit-backend.onrender.com

Currently, I'm using the free plan on the website to host the service, so in order to use it or test it, you need to access the url and wait for approximately one minute for the service to go live. After that, you can start using the API.


## API Reference

#### Register User

```http
  POST /auth/register
```
Json model:

{
  "firstName": "",
  "lastName": "",
  "email": "",
  "password": "",
  "username": "",
  "dni": ""
}

#### Register Login

```http
  POST /auth/login
```
Json model:

{
    "email": "",
    "password": ""
}

#### Get users by email

```http
  GET /api/user/email/${email}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `bearer_token` | `string` | **Required**. Obtained in the Login Response |
| `email` | `string` | **Required**. Searched email |

#### Get users by dni

```http
  GET /api/user/dni/${dni}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `bearer_token` | `string` | **Required**. Obtained in the Login Response |
| `dni` | `string` | **Required**. Searched dni |

#### Register Project

```http
  POST /api/projects
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `bearer_token` | `string` | **Required**. Obtained in the Login Response |

Json model:

{
    "name": "",
    "color": "",
    "user":
        {
            "id": 
        }
  
}

#### Get projects by project ID

```http
  GET /api/projects/${projectId}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `bearer_token` | `string` | **Required**. Obtained in the Login Response |
| `projectId` | `string` | **Required**. Searched project ID |

#### Get projects by user ID

```http
  GET /api/projects/user/${userId}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `bearer_token` | `string` | **Required**. Obtained in the Login Response |
| `userId` | `string` | **Required**. Searched user ID |

#### Get projects by project guest ID

```http
  GET /api/projects/guest/${id}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `bearer_token` | `string` | **Required**. Obtained in the Login Response |
| `id` | `string` | **Required**. Searched user ID |

#### Update Project

```http
  PUT /api/projects/${projectId}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `bearer_token` | `string` | **Required**. Obtained in the Login Response |
| `projectId` | `string` | **Required**. Searched project ID |

Json model:

{
    "name": "",
    "color": "",
    "user":
        {
            "id": 
        }
  
}

#### Delete Project

```http
  DELETE /api/projects/${projectId}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `bearer_token` | `string` | **Required**. Obtained in the Login Response |
| `projectId` | `string` | **Required**. Searched project ID |


#### Add Guest to Project

```http
  PATCH /api/projects/${projectId}/guests/${userId}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `bearer_token` | `string` | **Required**. Obtained in the Login Response |
| `projectId` | `string` | **Required**. Searched project ID |
| `userId` | `string` | **Required**. Searched user ID |

#### Delete Guest from Project

```http
  DELETE /api/projects/${projectId}/guests/${userId}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `bearer_token` | `string` | **Required**. Obtained in the Login Response |
| `projectId` | `string` | **Required**. Searched project ID |
| `userId` | `string` | **Required**. Searched user ID |

#### Get project's guests

```http
  GET /api/projects/${projectId}/guests
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `bearer_token` | `string` | **Required**. Obtained in the Login Response |
| `projectId` | `string` | **Required**. Searched project ID |

#### Register Task

```http
  POST /api/tasks
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `bearer_token` | `string` | **Required**. Obtained in the Login Response |

Json model:

{
  "title": "",
  "description": "",
  "priority": "",
  "dueDate": "",
  "project":
        {
            "": 
        },
    "user":
    {
        "id": 
    }
}

#### Get tasks by project ID

```http
  GET /api/tasks/project/${projectId}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `bearer_token` | `string` | **Required**. Obtained in the Login Response |
| `projectId` | `string` | **Required**. Searched project ID |

#### Get tasks by user ID

```http
  GET /api/tasks/user/${userId}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `bearer_token` | `string` | **Required**. Obtained in the Login Response |
| `userId` | `string` | **Required**. Searched user ID |

#### Get tasks by task ID

```http
  GET /api/tasks/${taskId}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `bearer_token` | `string` | **Required**. Obtained in the Login Response |
| `taskId` | `string` | **Required**. Searched task ID |

#### Update Task

```http
  PUT /api/tasks/${taskId}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `bearer_token` | `string` | **Required**. Obtained in the Login Response |
| `taskId` | `string` | **Required**. Searched task ID |

Json model:

{
  "title": "",
  "description": "",
  "priority": "",
  "dueDate": "",
  "project":
        {
            "": 
        },
    "user":
    {
        "id": 
    }
}

#### Delete Task

```http
  DELETE /api/tasks/${taskId}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `bearer_token` | `string` | **Required**. Obtained in the Login Response |
| `taskId` | `string` | **Required**. Searched task ID |

#### Update task status

```http
  PATCH /api/tasks/${taskId}/checked
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `bearer_token` | `string` | **Required**. Obtained in the Login Response |
| `taskId` | `string` | **Required**. Searched task ID |

#### Restore deleted task

```http
  PATCH /api/tasks/${taskId}/restore
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `bearer_token` | `string` | **Required**. Obtained in the Login Response |
| `taskId` | `string` | **Required**. Searched task ID |

#### Register comment

```http
  POST /api/comments
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `bearer_token` | `string` | **Required**. Obtained in the Login Response |

Json model:

{
    "description": "",
    "task":
    {
        "taskId": 
    },
    "user":
    {
        "id": 
    }
}

#### Get comment by comment ID

```http
  GET /api/comments/${id}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `bearer_token` | `string` | **Required**. Obtained in the Login Response |
| `id` | `string` | **Required**. Searched comment ID |

#### Get comment by user ID

```http
  GET /api/comments/user/${userId}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `bearer_token` | `string` | **Required**. Obtained in the Login Response |
| `userId` | `string` | **Required**. Searched user ID |

#### Get comment by task ID

```http
  GET /api/comments/task/${taskId}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `bearer_token` | `string` | **Required**. Obtained in the Login Response |
| `taskId` | `string` | **Required**. Searched task ID |

#### Delete comment

```http
  DELETE /api/comments/${id}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `bearer_token` | `string` | **Required**. Obtained in the Login Response |
| `id` | `string` | **Required**. Searched comment ID |

#### Restore deleted comment

```http
  PATCH /api/comments/${id}/restore
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `bearer_token` | `string` | **Required**. Obtained in the Login Response |
| `id` | `string` | **Required**. Searched comment ID |


