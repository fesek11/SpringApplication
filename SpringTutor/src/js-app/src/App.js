import React, {Component} from 'react';
import Container from "./Container";
import Footer from "./Footer";
import './App.css';
import {getAllStudents} from './client';
import AddStudentForm from './forms/AddStudentForm';
import {
    Avatar,
    Table,
    Spin,
    Modal
} from 'antd';
import {LoadingOutlined} from '@ant-design/icons';

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
                }));
    }

    render() {
        const {students, isFetching, isAddStudentModalVisible} = this.state;

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
                        dataSource={students}
                        columns={columns}
                        pagination={false}
                        rowKey='id'/>
                    <Modal
                        title="Add new student"
                        visible={isAddStudentModalVisible}
                        onOk={this.closeAddStudentModal}
                        onCancel={this.closeAddStudentModal}
                        width={600}>
                         <AddStudentForm/>
                    </Modal>
                    <Footer
                        numberOfStudents={students.length}
                        handleAddStudentClickEvent={this.openAddStudentModal}/>
                </Container>
            );
        }

        return <h1>No Students found </h1>;
    }
}

export default App;
