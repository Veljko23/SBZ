import React, { useState } from 'react';
import axios from 'axios';

const SuggestFruitComplex = () => {
    const token = localStorage.getItem("token");

    const [phVrednost, setPhVrednost] = useState('');
    const [kalcijumKarbonat, setKalcijumKarbonat] = useState('');
    const [azot, setAzot] = useState('');
    const [fosfor, setFosfor] = useState('');
    const [humus, setHumus] = useState('');
    const [kalijum, setKalijum] = useState('');
    const [region, setRegion] = useState('');
    const [output, setOutput] = useState('');

    const onSubmit = async () => {
        try {
            const response = await axios.post('http://localhost:8080/suggest/complex', {
                phVrednost,
                kalcijumKarbonat,
                azot,
                fosfor,
                humus,
                kalijum,
                region
            }, {
                headers: {
                    "Content-Type": "application/json",
                    Authorization: token,
                }
            });
            const result = response.data;
            const recommendedFruits = result || [];
            let outputString = recommendedFruits.map(fruit => fruit.name).join(', ');
            setOutput(outputString || 'No suggestions');
        } catch (error) {
            console.error('Error fetching data', error);
            setOutput('Error fetching suggestions');
        }
    };

    return (
        <div>
            <h2>Add Zemljiste</h2>
            <div>
                <label>pH Vrednost:</label>
                <input
                    type="number"
                    value={phVrednost}
                    onChange={(e) => setPhVrednost(e.target.value)}
                />
            </div>
            <div>
                <label>Kalcijum Karbonat:</label>
                <input
                    type="number"
                    value={kalcijumKarbonat}
                    onChange={(e) => setKalcijumKarbonat(e.target.value)}
                />
            </div>
            <div>
                <label>Azot:</label>
                <input
                    type="number"
                    value={azot}
                    onChange={(e) => setAzot(e.target.value)}
                />
            </div>
            <div>
                <label>Fosfor:</label>
                <input
                    type="number"
                    value={fosfor}
                    onChange={(e) => setFosfor(e.target.value)}
                />
            </div>
            <div>
                <label>Humus:</label>
                <input
                    type="number"
                    value={humus}
                    onChange={(e) => setHumus(e.target.value)}
                />
            </div>
            <div>
                <label>Kalijum:</label>
                <input
                    type="number"
                    value={kalijum}
                    onChange={(e) => setKalijum(e.target.value)}
                />
            </div>
            <div>
                <label>Region:</label>
                <input
                    type="text"
                    value={region}
                    onChange={(e) => setRegion(e.target.value)}
                />
            </div>
            <button onClick={onSubmit}>Generiši</button>
            {output && (
                <div>
                    <h3>Preporučene sorte voća:</h3>
                    <p>{output}</p>
                </div>
            )}
        </div>
    );
};

export default SuggestFruitComplex;
