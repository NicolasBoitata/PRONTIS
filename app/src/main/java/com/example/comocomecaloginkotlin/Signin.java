package com.example.comocomecaloginkotlin;

import androidx.appcompat.app.AppCompatActivity;

public class Signin extends AppCompatActivity {


//        const SignIn = ({navigation}) => {
//        const [email, setEmail] = useState('');
//        const [pass, setPass] useState('');
//
//        const recuperarSenha = () => {
//            navigation.navigate('Forgot Password');
//        };
//
//        const entrar = () => {
//            if (email !== '' && pass !== '') {
//                auth()
//                .signInWithEmailAndPassword(email, pass)
//                .then(() => {
//                    if (!auth().currentUser.emailVerified){
//                        Alert.alert(
//                            'Erro',
//                            'Você deve verificar o seu email para prosseguir.',
//                        );
//                        return;
//                    }
//                    navigation.dispatch(
//                        CommonActions.reset({
//                        index: 0,
//                        routes: [{name: 'Home'}],
//                    }),
//                );
//            })
//        .catch((e) => {
//        console.log('SignIn: erro em entrar: ' + e);
//        switch (e.code) {
//            case 'auth/email-already-in-use':
//                Alert.alert('Erro', 'Email já está em uso.');
//                break;
//            case 'auth/operation-not-allowed':
//                Alert.alert('Erro', 'Poblemas ao cadastrar o usuário.');
//                break;
//            case 'auth/operation-not-allowed':
//                Alert.alert('Erro', 'Email inválido.');
//                break;
//            case 'auth/weak-password':
//                Alert.alert(
//                        'Erro',
//                        'Senha é fraca, por favor, digete uma senha forte.',
//                break;
//                }
//            });
//        } else {
//            Alert.alert('Erro', 'Por favor, digite email e senha.');
//         }
//        };
//
//        const cadastrar = () = {
//            navigation.navigate('SignUp');
//        };
//    }
}
