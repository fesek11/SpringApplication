import React from 'react';
import {Formik} from "formik";
import {Input, Button, Tag} from 'antd';
import {addNewStudent} from "../client";

const inputBottomMargin = {marginBottom: '5px'};
const tagStyle = {backgroundColor: '#f50', color: 'white', ...inputBottomMargin};

const AddStudentForm = (props) => (
    <Formik
        initialValues={{firstName: '', email: '', age: ''}}
        validate={values => {
            let errors = {};
            if (!values.firstName) {
                errors.firstName = 'Required name';
            }
            if (!values.email) {
                errors.email = 'Required email';
            } else if (
                !/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i.test(values.email)
            ) {
                errors.email = 'Invalid email address';
            }
            if (!values.age) {
                errors.age = 'Required age';
            } else if (
                !/^\d{1,3}$/i.test(values.age)
            ) {
                errors.email = 'Invalid age';
            }

            return errors;
        }}
        onSubmit={(student, {setSubmitting}) => {
            addNewStudent(student).then(() => {
                this.props.onSuccess();
                // setSubmitting(false);
            })
                .catch(err => {
                    props.onFailure(err);
                })
                .finally(() => {
                    setSubmitting(false);
                })
        }}>
        {({
              values,
              errors,
              touched,
              handleChange,
              handleBlur,
              handleSubmit,
              isSubmitting,
              submitForm,
              isValid
              /* and other goodies */
          }) => (
            <form onSubmit={handleSubmit}>
                <Input
                    style={inputBottomMargin}
                    name="firstName"
                    type="firstName"
                    onChange={handleChange}
                    onBlur={handleBlur}
                    value={values.firstName}
                    placeholder='First name. E.g John'
                />
                {errors.firstName && touched.firstName && <Tag style={tagStyle}>
                    {errors.firstName}</Tag>}
                <Input
                    style={inputBottomMargin}
                    name="email"
                    type='email'
                    onChange={handleChange}
                    onBlur={handleBlur}
                    value={values.email}
                    placeholder='Email'
                />
                {errors.email && touched.email && <Tag style={tagStyle}>
                    {errors.email}</Tag>}
                <Input
                    style={inputBottomMargin}
                    name="age"
                    type='age'
                    onChange={handleChange}
                    onBlur={handleBlur}
                    value={values.age}
                    placeholder='age'
                />
                {errors.age && touched.age && <Tag style={tagStyle}>
                    {errors.age}</Tag>}

                <Button
                    onClick={() => submitForm()}
                    type="submit"
                    disabled={isSubmitting | (touched && !isValid)}>
                    Submit
                </Button>
            </form>
        )}
    </Formik>
);

export default AddStudentForm;
