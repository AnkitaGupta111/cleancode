import React, { useState } from "react";
import { makeStyles } from "@material-ui/core/styles";
import Grid from "@material-ui/core/Grid";
import Typography from "@material-ui/core/Typography";
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import axios from "axios";

const useStyles = makeStyles((theme) => ({
  appBar: {
    position: "relative",
  },

  stepper: {
    padding: theme.spacing(3, 0, 5),
  },
  buttons: {
    display: "flex",
    justifyContent: "flex-end",
  },
  button: {
    marginTop: theme.spacing(3),
    marginLeft: theme.spacing(1),
  },
}));

export default function Rules(props) {
  const classes = useStyles();
  const [rule, setRule] = useState(props.rule);
  const frameOptions = (e) => {
    e.preventDefault();
    let value = e.target.value;
    let options = JSON.parse(value);
    console.log("options", options);
    setRule({ ...rule, options: options });
  };
  const frameRows = (e) => {
    e.preventDefault();
    let value = e.target.value;
    let rows = JSON.parse(value);
    console.log("rows", rows);
    setRule({ ...rule, rows: rows });
  };
  const frameHeaders = (e) => {
    e.preventDefault();
    let value = e.target.value;
    let headers = JSON.parse(value);
    console.log("headers", headers);
    setRule({ ...rule, headers: headers });
  };

  function handleSubmit(event) {
    event.preventDefault();
    let request = {
      url: "http://localhost:8000/rules",
      method: "POST",
      data: rule,
      headers: {
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Methods": "GET,PUT,POST,DELETE,PATCH,OPTIONS",
      },
    };

    console.log("request", request);
    axios(request)
      .then((resp) => {
        if (resp.status === 200) {
          alert("Success API call");
        }
      })
      .catch((err) => {
        alert("Failed API call", err);
      });
  }
  return (
    <React.Fragment>
      <React.Fragment>
        <Typography variant="h6" gutterBottom>
          New Rule
        </Typography>
        <Grid container spacing={3}>
          <Grid item xs={12}>
            <TextField
              required
              label="Name"
              value={rule.name}
              fullWidth
              autoComplete="given-name"
              onInput={(e) => setRule({ ...rule, name: e.target.value })}
            />
          </Grid>
          <Grid item xs={12} sm={6}>
            <TextField
              required
              value={rule.description}
              label="Description"
              fullWidth
              autoComplete="description"
              onInput={(e) => setRule({ ...rule, description: e.target.value })}
            />
          </Grid>
          <Grid item xs={12} sm={6}>
            <TextField
              id="artifactId"
              value={rule.artifactId}
              label="Artifact Id"
              fullWidth
              onInput={(e) => setRule({ ...rule, artifactId: e.target.value })}
            />
          </Grid>
          <Grid item xs={12}>
            <TextField
              label="Options"
              fullWidth
              onBlur={frameOptions}
            />
          </Grid>
          <Grid item xs={12}>
            <TextField label="Headers" fullWidth headers onBlur={frameHeaders} />
          </Grid>
          <Grid item xs={12}>
            <TextField label="Rows" fullWidth onBlur={frameRows} />
          </Grid>
        </Grid>
      </React.Fragment>
      <React.Fragment>
        <div className={classes.buttons}>
          <Button variant="contained" color="primary" className={classes.button} onClick={handleSubmit}>
            {"Submit"}
          </Button>
        </div>
      </React.Fragment>
    </React.Fragment>
  );
}
