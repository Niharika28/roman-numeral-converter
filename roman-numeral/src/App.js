import {
  Button,
  defaultTheme,
  Provider,
  Form,
  TextField,
  Flex,
  View,
  Text,
  Heading,
} from "@adobe/react-spectrum";
import React, { useState, useEffect } from "react";

function App() {
  //Store input value in inputNumber
  const [inputNumber, setInputNumber] = useState("");
  // result will be stored in the romanNumeral
  const [romanNumeral, setRomanNumeral] = useState("");
  //incase of error/exception
  const [error, setError] = useState("");
  //This is to set color based on the system settings
  const [colorScheme, setColorScheme] = useState("light");

  //Detect system color schemes
  useEffect(() => {
    const systemColor = window.matchMedia("(prefers-color-scheme: dark)");
    //function sets the colorScheme based on the system color
    const handleChange = () => {
      setColorScheme(systemColor.matches ? "dark" : "light");
    };

    handleChange();
    systemColor.addEventListener("change", handleChange);
    return () => systemColor.removeEventListener("change", handleChange);
  }, []);

  //function handles button click event
  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    setRomanNumeral(null);

    if (inputNumber === "") {
      setError("Enter valid number");
      return;
    }

    try {
      // calling backend api using fetch to convert given number to roman numeral
      const response = await fetch(
        "http://localhost:8080/api/romannumeral?input=" + inputNumber
      );
      console.log("Response status ::" + response.ok);
      if (!response.ok) {
        const errorData = await response.json();
        const errorMessage = errorData.errors?.[0]?.title || "Unkown Error";
        setError(errorMessage);
        return;
      }
      const data = await response.json();
      console.log("Roman numeral ::" + data.output);
      setRomanNumeral(data.output);
    } catch (err) {
      setError("Somthing fail while calling api" + err);
    }
  };

  return (
    <Provider theme={defaultTheme} colorScheme={colorScheme}>
      <Flex direction="column" gap="size-500">
        <View padding={"size-300"}>
          <Heading level={2} style={{ color: "blue" }}>
            Roman Numeral Converter
          </Heading>
          <br />
          <Form
            maxWidth="size-3600"
            aria-labelledby="label-3"
            onSubmit={handleSubmit}
          >
            <View
              borderWidth="thin"
              borderColor="dark"
              borderRadius="medium"
              padding="size-250"
            >
              <TextField
                label="Enter a number"
                value={inputNumber}
                onChange={setInputNumber}
                isRequired
                necessityIndicator="label"
                width="size-2000"
                type="number"
              />
            </View>
            <br />
            <Button variant="accent" type="submit">
              Convert to roman numeral
            </Button>
          </Form>
          <br />
          {romanNumeral !== null && !error && (
            <Text marginTop={"size-200"} style={{ color: "green" }}>
              Roman Numeral: {romanNumeral}
            </Text>
          )}
          {error && (
            <Text marginTop={"size-200"} UNSAFE_style={{ color: "red" }}>
              {error}
            </Text>
          )}
        </View>
      </Flex>
    </Provider>
  );
}

export default App;
