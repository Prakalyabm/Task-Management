<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Todo List</title>
    <link rel="stylesheet" href="css/todo.css">
</head>

<body>
    <img src="images/pic2.png" alt="Image" />
    <h2>Todo List</h2>

    <!-- Form to add tasks -->
    <form onsubmit="event.preventDefault(); addTask();">
        <label for="sno">S.No:</label>
        <input type="text" id="sno" name="sno" required>

        <label for="taskName">Task Name:</label>
        <input type="text" id="taskName" name="taskName" required>

        <label for="startTime">Start Time:</label>
        <input type="time" id="startTime" name="startTime" required>

        <label for="endTime">End Time:</label>
        <input type="time" id="endTime" name="endTime" required>

        <button type="submit">Add Task</button>
    </form>

    <!-- Table to display tasks -->
    <table id="taskTable">
        <thead>
            <tr>
                <th>S.No</th>
                <th>Task Name</th>
                <th>Start Time</th>
                <th>End Time</th>
                <th>Options</th>
            </tr>
        </thead>
        <tbody>
            <!-- Tasks will be dynamically added here using JavaScript -->
        </tbody>
    </table>

    <script>
        document.addEventListener('DOMContentLoaded', function () {
            loadTasks();
        });

        <!-- Update the addTask function in your script -->
        function addTask() {
            // Get form values
            const sno = document.getElementById("sno").value;
            const taskName = document.getElementById("taskName").value;
            const startTime = document.getElementById("startTime").value;
            const endTime = document.getElementById("endTime").value;

            // Create a new row for the task
            const newRow = document.createElement("tr");

            // Create individual cells for each column
            const snoCell = document.createElement("td");
            snoCell.textContent = sno;
            const taskNameCell = document.createElement("td");
            taskNameCell.textContent = taskName;
            const startTimeCell = document.createElement("td");
            startTimeCell.textContent = startTime;
            const endTimeCell = document.createElement("td");
            endTimeCell.textContent = endTime;
            const optionsCell = document.createElement("td");
            const editButton = document.createElement("button");
            editButton.textContent = "Edit";
            editButton.onclick = function () {
                editTask(this);
            };
            const deleteButton = document.createElement("button");
            deleteButton.textContent = "Delete";
            deleteButton.onclick = function () {
                deleteTask(this);
            };
            optionsCell.appendChild(editButton);
            optionsCell.appendChild(deleteButton);

            // Append cells to the row
            newRow.appendChild(snoCell);
            newRow.appendChild(taskNameCell);
            newRow.appendChild(startTimeCell);
            newRow.appendChild(endTimeCell);
            newRow.appendChild(optionsCell);

            // Append the new row to the table body
            const tbody = document.getElementById("taskTable").getElementsByTagName('tbody')[0];
            tbody.appendChild(newRow);

            // Save tasks
            saveTasks();

            // Clear form fields
            document.getElementById("sno").value = "";
            document.getElementById("taskName").value = "";
            document.getElementById("startTime").value = "";
            document.getElementById("endTime").value = "";
        }



        function editTask(button) {
            // Get the row of the clicked button
            const row = button.parentNode.parentNode;

            // Retrieve data from the row
            const sno = row.cells[0].innerHTML;
            const taskName = row.cells[1].innerHTML;
            const startTime = row.cells[2].innerHTML;
            const endTime = row.cells[3].innerHTML;

            // Update the form with the retrieved data
            document.getElementById("sno").value = sno;
            document.getElementById("taskName").value = taskName;
            document.getElementById("startTime").value = startTime;
            document.getElementById("endTime").value = endTime;

            saveTasks();

            // Remove the row from the table
            row.remove();
        }

        function deleteTask(button) {
            // Get the row of the clicked button and remove it
            const row = button.parentNode.parentNode;
            row.remove();
            saveTasks();
        }

        function saveTasks() {
            // Get the tasks from the table
            const tasks = [];
            const tableRows = document.getElementById("taskTable").getElementsByTagName('tbody')[0].getElementsByTagName('tr');
            for (const row of tableRows) {
                const task = {
                    sno: row.cells[0].innerHTML,
                    taskName: row.cells[1].innerHTML,
                    startTime: row.cells[2].innerHTML,
                    endTime: row.cells[3].innerHTML
                };
                tasks.push(task);
            }

            // Save tasks to local storage
            localStorage.setItem('tasks', JSON.stringify(tasks));
        }

        function loadTasks() {
            // Retrieve tasks from local storage
            const savedTasks = localStorage.getItem('tasks');

            if (savedTasks) {
                const tasks = JSON.parse(savedTasks);

                // Create rows for each task
                for (const task of tasks) {
                    const newRow = document.createElement("tr");
                    newRow.innerHTML = `
                        <td>${task.sno}</td>
                        <td>${task.taskName}</td>
                        <td>${task.startTime}</td>
                        <td>${task.endTime}</td>
                        <td>
                            <button onclick="editTask(this)">Edit</button>
                            <button onclick="deleteTask(this)">Delete</button>
                        </td>
                    `;
                    document.getElementById("taskTable").getElementsByTagName('tbody')[0].appendChild(newRow);
                }
            }
        }
    </script>
</body>

</html>