import { useEffect, useState } from 'react';
import UserList from '../components/UserList';
import FieldService from '../services/FieldService';

function MainPage() {
  const [posts, setPosts] = useState([])

  useEffect(() => {
    fetchPosts()
  }, [])

  async function fetchPosts() {
    const response = await FieldService.getField();

    if(response.status!==500){
      console.log(response.data)
      setPosts(response.data)
    }else{
      alert("error from server")
    }
    
  }

  return (
    <div className="App">
      <UserList posts={posts} />
    </div>
  );
}

export default MainPage;