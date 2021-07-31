import React, {Component} from 'react';
import Container from "./Container";
import Footer from "./Footer";
import './App.css';
import {getAllStudents} from './client';
import AddStudentForm from './forms/AddStudentForm';
import {Avatar, Empty, Modal, Spin, Table} from 'antd';
import {LoadingOutlined} from '@ant-design/icons';
import {errorNotification} from "./Notification";

const getIndicatorIcon = () => <LoadingOutlined style={{fontSize: 24}} spin/>;

class App extends Component {
    state = {
        students: [],
        isFetching: false,
        isAddStudentModalVisible: false
    }


    componentDidMount () {
        this.fetchStudents();
    }

    openAddStudentModal = () => this.setState({isAddStudentModalVisible: true})
    closeAddStudentModal = () => this.setState({isAddStudentModalVisible: false})


    fetchStudents = () => {
        this.setState({
            isFetching: true
        });
        getAllStudents()
            .then(res => res.json()
                .then(students => {
                    console.log(students);
                    this.setState({
                        students,
                        isFetching: false
                    });
                }))
            .catch(error => {
                console.log(error.error);
                const message = error.error.message;
                const description = error.errors.error;
                errorNotification(message, description)
                this.setState({
                    isFetching: false
                });
            });
    }

    render() {
        const {students, isFetching, isAddStudentModalVisible} = this.state;
        const commonElements = () => (
            <div>
                <Modal
                    title="Add new student"
                    visible={isAddStudentModalVisible}
                    onOk={this.closeAddStudentModal}
                    onCancel={this.closeAddStudentModal}
                    width={600}>
                    <AddStudentForm
                        onSuccess={() => {
                            this.closeAddStudentModal();
                            this.fetchStudents();
                        }}
                        onFailure={(error) => {
                            const message = error.error.message;
                            const description = error.error.error;
                            errorNotification(message, description);
                        }}

                    />
                </Modal>
                <Footer
                    numberOfStudents={students.length}
                    handleAddStudentClickEvent={this.openAddStudentModal}/>
            </div>
        );

        if (isFetching) {
            return (
                <Container>
                    <Spin indicator={getIndicatorIcon}/>
                </Container>
            );
        }
        if (students && students.length) {

            const columns = [
                {
                    title: '',
                    key: 'avatar',
                    render: (text, student) => (
                        <Avatar size='large'>
                            {`${student.name.charAt(0).toUpperCase()}${student.email.charAt(0).toUpperCase()}`}
                        </Avatar>
                    )
                },
                {
                    title: 'StudentId',
                    dataIndex: 'id',
                    key: 'id'
                },
                {
                    title: 'StudentName',
                    dataIndex: 'name',
                    key: 'name'
                },
                {
                    title: 'StudentEmail',
                    dataIndex: 'email',
                    key: 'email'
                },
                {
                    title: 'StudentAge',
                    dataIndex: 'age',
                    key: 'age'
                }
            ];

            return (
                <Container>
                    <Table
                        style={{marginBottom: '100px'}}
                        dataSource={students}
                        columns={columns}
                        pagination={false}
                        rowKey='id'/>
                    {commonElements()}
                </Container>
            );
        }

        return (
            <Container>
            <Empty description={
            <h1>No Students Found</h1>
            }/>
            {commonElements()}
        </Container>
        )
    }
}

export default App;
