import React, {Component} from 'react';
import {Formik} from "formik";
import {Input, Button, Tag} from 'antd';

const inputBottomMargin = {marginBottom: '5px'};
const tagStyle = {backgroundColor: '#f50', color: 'white', ...inputBottomMargin};

class AddStudentForm extends Component {
    render() {
        return (
            <Formik
                initialValues={{firstName : '', email: '', age: ''}}
                validate={values => {
                    const errors = {};
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
                onSubmit={(values, {setSubmitting}) => {
                    setTimeout(() => {
                        alert(JSON.stringify(values, null, 2));
                        setSubmitting(false);
                    }, 400);
                }}
            >
                {({
                      values,
                      errors,
                      touched,
                      handleChange,
                      handleBlur,
                      handleSubmit,
                      isSubmitting,
                      submitForm
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

                        <Button onClick={() => submitForm()} type="submit" disabled={isSubmitting}>
                            Submit
                        </Button>
                    </form>
                )}
            </Formik>
        );
    }
}

export default AddStudentForm;
