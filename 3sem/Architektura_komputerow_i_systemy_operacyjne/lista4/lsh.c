#include <sys/wait.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

void sigchld_handler(int sig) {
  while (waitpid(-1, NULL, WNOHANG) > 0);
}

int lsh_cd(char **command) {
  if (command[1] == NULL) {
    fprintf(stderr, "lsh: oczekiwano argumentu dla \"cd\"\n");
  } else {
    if (chdir(command[1]) != 0) {
      perror("lsh");
    }
  }
  return 1;
}

int lsh_exit(char **command) {
  return 0;
}

int lsh_launch(char **command) {
  int background = 0;

  int i = 0;
  while (command[i] != NULL) i++;
  if (i > 0 && strcmp(command[i - 1], "&") == 0) {
    background = 1;
    command[i - 1] = NULL;
  }

  pid_t pid = fork();
  if (pid == 0) {
    if (execvp(command[0], command) == -1) {
      perror("lsh");
    }
    exit(EXIT_FAILURE);
  } else if (pid < 0) {
    perror("lsh");
  } else {
    if (!background) {
      wait(NULL);
    }
  }
  return 1;
}


int lsh_execute(char **command) {
  if (command[0] == NULL) return 1;

  if (strcmp(command[0], "cd") == 0) {
    return lsh_cd(command);
  } else if (strcmp(command[0], "exit") == 0) {
    return lsh_exit(command);
  }
  return lsh_launch(command);
}

char *lsh_read_line() {
  char *line = NULL;
  size_t bufsize = 0;

  if (getline(&line, &bufsize, stdin) == -1) {
    free(line);
    return NULL;
  }
  return line;
}

char **lsh_split_line(char *line) {
  int bufsize = 64;
  int position = 0;
  char **tokens = malloc(bufsize * sizeof(char *));
  char *token = strtok(line, " \t\r\n\a");

  while (token != NULL) {
    tokens[position++] = token;
    token = strtok(NULL, " \t\r\n\a");
  }
  tokens[position] = NULL;
  return tokens;
}

void lsh_loop(void) {
  char *line;
  char **command;
  int status = 1;

  while (status) {
    printf("> ");
    line = lsh_read_line();
    if (line == NULL) {
      printf("\n");
      break;
    }
    command = lsh_split_line(line);
    status = lsh_execute(command);
    free(line);
    free(command);
  }
}

int main() {
  signal(SIGCHLD, sigchld_handler);
  lsh_loop();
  return 0;
}
