using Clinic.Models;

namespace Clinic.DataAccess.Repository.IRepository
{
    public interface IPatientRepository : IRepositoryAsync<Patient>
    {
        void Update(Patient patient);
    }
}