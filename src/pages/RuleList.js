import React from "react";
import { makeStyles } from "@material-ui/core/styles";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableCell from "@material-ui/core/TableCell";
import TableContainer from "@material-ui/core/TableContainer";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
import Paper from "@material-ui/core/Paper";

const useStyles = makeStyles({
  table: {
    minWidth: 100,
    maxWidth: 400,
  },
});

function createData(name, description, artifactId) {
  return { name, description, artifactId };
}

const rows = [createData("RuleName", "Description", "ArtifactId")];

export default function RuleList(props) {
  const classes = useStyles();
  const rules = rows;
  return (
    <TableContainer component={Paper}>
      <Table className={classes.table} size="small" aria-label="a dense table">
        <TableHead>
          <TableRow>
            <TableCell>Rule Name</TableCell>
            <TableCell align="right">Description</TableCell>
            <TableCell align="right">ArtifactId</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {rules.map((rule) => {
            return (
              <TableRow key={rule.name}>
                <TableCell component="th" scope="row">
                  {rule.name}
                </TableCell>
                <TableCell align="right">{rule.description}</TableCell>
                <TableCell align="right">{rule.artifactId}</TableCell>
              </TableRow>
            );
          })}
        </TableBody>
      </Table>
    </TableContainer>
  );
}
